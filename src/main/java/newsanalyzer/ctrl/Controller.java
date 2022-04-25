package newsanalyzer.ctrl;

import newsapi.NewsApi;
import newsapi.NewsApiBuilder;
import newsapi.beans.Article;
import newsapi.beans.NewsApiException;
import newsapi.beans.NewsReponse;
import newsapi.enums.Category;
import newsapi.enums.Country;
import newsapi.enums.Endpoint;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {

	public static final String APIKEY = "214ade2bcab9482da030e57af2fdb48a";

	public void process(NewsApi Ctrl) {
		System.out.println("Start process");

		//TODO implement Error handling

		//TODO load the news based on the parameters

		//TODO implement methods for analysis


		System.out.println("End process");

	}



	public Object getData(String title, Endpoint endpoint, Country country, Category category) {
		try {
			NewsApi newsApi = new NewsApiBuilder()
					.setApiKey(APIKEY)
					.setQ(title)
					.setEndPoint(endpoint)
					.setSourceCountry(country)
					.setSourceCategory(category)
					.createNewsApi();

			NewsReponse newsResponse = newsApi.getNews();
			if (newsResponse != null) {
				List<Article> articles = newsResponse.getArticles();
				articles.stream().forEach(article -> System.out.println(article.toString()));
				return articles;
			} else
				return new NewsApiException("Failed to recive Data.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("End process");
		return null;
	}
	private Article getShortestName(List<Article> articles) {
		Article ShortestName = articles.stream()
				.filter(au -> au.getAuthor() != null)
				.sorted(Comparator.comparingInt(value -> value.getAuthor().length()))
				.findFirst()
				.orElse(new Article());
		return ShortestName;
	}

	private List<String> getAlphabetical(List<Article> articles){
		return articles.stream()
				.sorted(Comparator.comparing(Article::getTitle))
				.map(Article::getTitle)
				.collect(Collectors.toList());
	}

	private long getCount(List<Article> articles){
		long Count = articles.stream().count();
		return Count;
	}


}


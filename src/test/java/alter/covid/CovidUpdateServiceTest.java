package alter.covid;

import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class CovidUpdateServiceTest {

    @Test
    public void getCovidUpdate() throws IOException {
        //given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://covidapi.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CovidUpdateService service = retrofit.create(CovidUpdateService.class);

        //when
        CovidUpdateFeed feed = service.getCovidUpdate("USA", "2020-04-01", "2020-04-02").execute().body();

        //then
        assertNotNull(feed);
        List<CovidUpdateFeed.Result> covidResults = feed.result;
        assertFalse(covidResults.isEmpty());
        CovidUpdateFeed.Result covidInfo = covidResults.get(0);
        assertNotNull(covidInfo.confirmed);
        assertNotNull(covidInfo.deaths);
        assertNotNull(covidInfo.recovered);

    }
}
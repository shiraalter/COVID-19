package alter.covid;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface CovidUpdateService {


    @GET("/api/v1/country/{country}/timeseries/{startDate}/{endDate}")
    //do i need .Result? when I took it out, everything worked in the test
    Call<CovidUpdateFeed> getCovidUpdate(@Path(value = "country") String countryCode,
                                                 @Path(value = "startDate") String startDate,
                                                 @Path(value = "endDate") String endDate);

}


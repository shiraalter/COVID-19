package alter.covid;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface CovidUpdateService {


    @GET("/api/v1/country/{country}/timeseries/{startDate}/{endDate}")

    Call<CovidUpdateFeed> getCovidUpdate(@Path(value = "country") String countryCode,
                                                 @Path(value = "startDate") String startDate,
                                                 @Path(value = "endDate") String endDate);

}


package alter.covid;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidUpdateController {
    private CovidUpdateService service;

    public CovidUpdateController(CovidUpdateService service) {
        this.service = service;
    }

    public void requestData(String country, String startDate, String endDate){
        service.getCovidUpdate(country, startDate, endDate ).enqueue(new Callback<CovidUpdateFeed>() {

            @Override
            public void onResponse(Call<CovidUpdateFeed> call, Response<CovidUpdateFeed> response) {
                CovidUpdateFeed.Result covidResults = response.body().result.get(0);
                CovidFrame.countryOutput.setText("Country: " + country);
                CovidFrame.confirmedLabel.setText("Total Confirmed Cases: " + String.valueOf(covidResults.confirmed));
                CovidFrame.deathsLabel.setText("Total Deaths: " + String.valueOf(covidResults.deaths));
                CovidFrame.recoveredLabel.setText("Total Recovered: " + String.valueOf(covidResults.recovered));

            }

            @Override
            public void onFailure(Call<CovidUpdateFeed> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}

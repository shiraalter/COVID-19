package alter.covid;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;

public class CovidUpdateController {

    private CovidUpdateService service;
    CovidUpdateFeed.Result covidResults;


    public CovidUpdateController(CovidUpdateService service) {
        this.service = service;
     }

    public void requestData(String country, String startDate, String endDate, JLabel countryLabel, JLabel confirmedLabel, JLabel deathsLabel, JLabel recoveredLabel, JLabel dateLabel){
        service.getCovidUpdate(country, startDate, endDate ).enqueue(new Callback<CovidUpdateFeed>() {

            @Override
            public void onResponse(Call<CovidUpdateFeed> call, Response<CovidUpdateFeed> response) {
                countryLabel.setText("Country: " + country);
                for (int i = 0; i < response.body().result.size() ; i++) {
                    covidResults = response.body().result.get(i);
                    dateLabel.setText("Date: " + covidResults.date);
                    confirmedLabel.setText("Confirmed Cases: " + (covidResults.confirmed));
                    deathsLabel.setText("Deaths: " + covidResults.deaths);
                    recoveredLabel.setText("Recovered: " + (covidResults.recovered));
                }



            }

            @Override
            public void onFailure(Call<CovidUpdateFeed> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}

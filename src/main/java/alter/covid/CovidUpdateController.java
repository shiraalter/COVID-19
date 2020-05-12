package alter.covid;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;

public class CovidUpdateController {
    private JLabel countryLabel;
    private JLabel confirmedLabel;
    private JLabel deathsLabel;
    private JLabel recoveredLabel;
    private CovidUpdateService service;
    CovidUpdateFeed.Result covidResults;


    public CovidUpdateController(CovidUpdateService service, JLabel countryLabel, JLabel confirmedLabel, JLabel deathsLabel, JLabel recoveredLabel) {
        this.service = service;
        this.countryLabel = countryLabel;
        this.confirmedLabel = confirmedLabel;
        this.deathsLabel = deathsLabel;
        this.recoveredLabel = recoveredLabel;


    }

    public void requestData(String country, String startDate, String endDate){
        service.getCovidUpdate(country, startDate, endDate ).enqueue(new Callback<CovidUpdateFeed>() {

            @Override
            public void onResponse(Call<CovidUpdateFeed> call, Response<CovidUpdateFeed> response) {
                covidResults = response.body().result.get(0);
                countryLabel.setText("Country: " + country);
                confirmedLabel.setText("Confirmed Cases: " + String.valueOf(covidResults.confirmed));
                deathsLabel.setText("Deaths: " + String.valueOf(covidResults.deaths));
                recoveredLabel.setText("Recovered: " + String.valueOf(covidResults.recovered));

            }

            @Override
            public void onFailure(Call<CovidUpdateFeed> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}

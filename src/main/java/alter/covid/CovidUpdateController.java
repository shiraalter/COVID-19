
package alter.covid;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;

public class CovidUpdateController {

    private CovidUpdateService service;
    private CovidView view;


    public CovidUpdateController(CovidUpdateService service, CovidView view) {
        this.service = service;
        this.view = view;
    }

    public void requestData(String country, String startDate, String endDate, JLabel countryLabel){
        service.getCovidUpdate(country, startDate, endDate ).enqueue(new Callback<CovidUpdateFeed>() {

            @Override
            public void onResponse(Call<CovidUpdateFeed> call, Response<CovidUpdateFeed> response) {
                countryLabel.setText("Country: " + country);

                for (int i = 0; i < response.body().result.size(); i++){
                    CovidUpdateFeed.Result covidResults = response.body().result.get(i);
                    view.setCovid(covidResults);

            }
        }
            @Override
            public void onFailure(Call<CovidUpdateFeed> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
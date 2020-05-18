package alter.covid;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.sound.midi.MidiDevice;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;



public class CovidFrame extends JFrame {

    private JLabel message;
    public JLabel confirmedLabel;
    public JLabel countryOutput;
    public JLabel deathsLabel;
    public JLabel recoveredLabel;
    public JLabel dateLabel;
    public JButton enterButton;

    private JPanel topPanel;
    private JPanel inputPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;
    private JPanel middlePanel;
    private JPanel infoPanel;

    private JLabel countryLabel;
    public JTextField countryField;
    private JLabel startLabel;
    public JTextField startField;
    private JLabel endLabel;
    public JTextField endField;


    public CovidFrame() throws IOException {
        setSize(900,900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("COVID-19 Update");
        setLayout(new BorderLayout());

        CovidView view = new CovidView();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://covidapi.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CovidUpdateService service = retrofit.create(CovidUpdateService.class);
        CovidUpdateController controller = new CovidUpdateController(service, view);


        topPanel = new JPanel();
        message = new JLabel("Welcome to the COVID-19 Tracker." +
                " Please enter your country code, a start date and an end date to view data");


        inputPanel = new JPanel(new GridLayout(4,1));   //gridlayout sub-panel for user input
        //country input:
        countryLabel = new JLabel("Enter Country Code: ");
        countryField = new JTextField();
        countryField.setPreferredSize(new Dimension(80,20));
        //start date input:
        startLabel = new JLabel("Enter Start Date (yyyy-mm-dd): ");
        startField = new JTextField();
        startField.setPreferredSize(new Dimension(80,20));
        //end date input:
        endLabel = new JLabel("Enter End Date (yyyy-mm-dd): ");
        endField = new JTextField();
        endField.setPreferredSize(new Dimension(80,20));
        //enter data button
        enterButton = new JButton("Enter");
        enterButton.setPreferredSize(new Dimension(100,40));


        topPanel.add(message);
        inputPanel.add(countryLabel);
        inputPanel.add(countryField);
        inputPanel.add(startLabel);
        inputPanel.add(startField);
        inputPanel.add(endLabel);
        inputPanel.add(endField);
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(enterButton);
        countryOutput = new JLabel();
        topPanel.add(countryOutput);
        add(topPanel, BorderLayout.NORTH);
        
        add(view, BorderLayout.CENTER);

        //action listener --> retrieve data
        enterButton.addActionListener(actionEvent -> getData(controller));





    }

    //request data from controller
    private void getData(CovidUpdateController controller) {
        controller.requestData(countryField.getText().toUpperCase(), startField.getText(), endField.getText(),  countryOutput);
    }

    public static void main(String[] args) throws IOException {
        new CovidFrame().setVisible(true);

    }


}

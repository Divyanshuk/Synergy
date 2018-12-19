package com.example.alihasan.synergytwo.Assignments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.alihasan.synergytwo.R;


public class PropertyActivity extends AppCompatActivity {

    static String SERVER_URL = "http://87408ed5.ngrok.io/project/aztekgo/android/";

    /**
     * Total 23
     * EditText  10
     * Spinner 13
     */

    EditText caseNo;

    EditText personContacted, area, documentVerify, neighbourName1, address1, neighbourName2, address2, propertySoldWhom, remarkPurchaser;

    Spinner easeToLocSpinner, relationshipSpinner, propTypeSpinner, noYearsPresentOwnSpinner, cooperativeAppSpinner, neighbourFeedSpinner,
            locTypeSpinner, ambienceSpinner, appStaySpinner, vehicalSeenSpinner ,politicalLinkSpinner, OverallStatusSpinner,
            reasonNegativeSpinner;

    /**
     * EDITTEXT AND SPINNER
     * STRING VALUES
     */

    String spersonContacted,
            sarea,
            sdocumentVerify,
            sneighbourName1,
            saddress1,
            sneighbourName2,
            saddress2,
            spropertySoldWhom,
            sremarkPurchaser;

    String  seaseToLocSpinner,
            srelationshipSpinner,
            spropTypeSpinner,
            snoYearsPresentOwnSpinner,
            scooperativeAppSpinner,
            sneighbourFeedSpinner,
            slocTypeSpinner,
            sambienceSpinner,
            sappStaySpinner,
            svehicalSeenSpinner,
            spoliticalLinkSpinner,
            sOverallStatusSpinner,
            sreasonNegativeSpinner;



    /**
     * SPINNER ADAPTER
     */

    ArrayAdapter<CharSequence> easeToLocSpinnerAdapter,
            relationshipSpinnerAdapter,
            propTypeSpinnerAdapter,
            noYearsPresentOwnAdapter,
            cooperativeAppSpinnerAdapter,
             neighbourFeedSpinnerAdapter,
            locTypeSpinnerAdapter,
             ambienceSpinnerAdapter,
            appStaySpinnerAdapter,
             vehicalSeenSpinnerAdapter,
            politicalLinkSpinnerAdapter,
             OverallStatusSpinnerAdapter,
            reasonNegativeSpinnerAdapter;

    /**
     * LOCATION VARIABLES
     */
    String slongi, slati;

    ProgressDialog dialog;

    Button nextButton, locationButton;

    TextView lat, lng;
    public static final int LOCATION_REQ_CODE = 100;
    LocationManager locationManager;
    private double latitude = 0;
    private double longitude = 0;

    Intent i = getIntent();
//        String StringCaseNo = i.getStringExtra("CASENO");
//    String userName = i.getStringExtra("USERNAME");

    String userName;
    String StringCaseNo;


    String BUSINESS_ACTIVITY = "PROPERTY";

    String TABLENAME = "cases-property";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        SharedPreferences loginData = getSharedPreferences("PDANO", Context.MODE_PRIVATE);
        userName = loginData.getString("PDANO", "");

        SharedPreferences caseData = getSharedPreferences("CASEDATA", Context.MODE_PRIVATE);
        StringCaseNo = caseData.getString("CASENO", "");


        //EditText
        personContacted = findViewById(R.id.personContacted);
        area = findViewById(R.id.area);
        documentVerify = findViewById(R.id.docVerify);
        neighbourName1 = findViewById(R.id.neighbourName1);
        address1 = findViewById(R.id.address1);
        neighbourName2 = findViewById(R.id.neighbourName2);
        address2 = findViewById(R.id.address2);
        propertySoldWhom = findViewById(R.id.propertySoldWhom);
        remarkPurchaser = findViewById(R.id.remarkPurchaser);

        //Spinner
        easeToLocSpinner = findViewById(R.id.easeLocSpinner);
        relationshipSpinner = findViewById(R.id.relationshipSpinner);
        propTypeSpinner = findViewById(R.id.propertyTypeSpinner);
        noYearsPresentOwnSpinner = findViewById(R.id.noYearPresentOwnSpinner);
        cooperativeAppSpinner = findViewById(R.id.cooperativeAppSpinner);
        neighbourFeedSpinner = findViewById(R.id.neighbourFeedbackSpinner);
        locTypeSpinner = findViewById(R.id.localityTypeSpinner);
        ambienceSpinner = findViewById(R.id.ambienceSpinner);
        appStaySpinner = findViewById(R.id.applicantStaySpinner);
        vehicalSeenSpinner = findViewById(R.id.vehicalSeenSpinner);
        politicalLinkSpinner = findViewById(R.id.polLinkSpinner);
        OverallStatusSpinner = findViewById(R.id.overallStatusSpinner);
        reasonNegativeSpinner = findViewById(R.id.reasonNegativeSpinner);

        nextButton = findViewById(R.id.nextButton);
        locationButton = findViewById(R.id.locationButton);

        lat = findViewById(R.id.lat);
        lng = findViewById(R.id.lng);


        /**
         * SETTING SPINNER ADAPTERS
         */
        //easeToLocSpinnerAdapter,
        //            relationshipSpinnerAdapter,
        //            propTypeSpinnerAdapter,
        //            noYearsPresentOwnAdapter,
        //            cooperativeAppSpinnerAdapter,
        //             neighbourFeedSpinnerAdapter,
        //            locTypeSpinnerAdapter,
        //             ambienceSpinnerAdapter,
        //            appStaySpinnerAdapter,
        //             vehicalSeenSpinnerAdapter,
        //            politicalLinkSpinnerAdapter,
        //             OverallStatusSpinnerAdapter,
        //            reasonNegativeSpinnerAdapter;

        easeToLocSpinnerAdapter = ArrayAdapter.createFromResource(PropertyActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        easeToLocSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        easeToLocSpinner.setAdapter(easeToLocSpinnerAdapter);

        relationshipSpinnerAdapter = ArrayAdapter.createFromResource(PropertyActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        relationshipSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        relationshipSpinner.setAdapter(relationshipSpinnerAdapter);

        propTypeSpinnerAdapter = ArrayAdapter.createFromResource(PropertyActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        propTypeSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        propTypeSpinner.setAdapter(propTypeSpinnerAdapter);

        noYearsPresentOwnAdapter = ArrayAdapter.createFromResource(PropertyActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        noYearsPresentOwnAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        noYearsPresentOwnSpinner.setAdapter(noYearsPresentOwnAdapter);

        cooperativeAppSpinnerAdapter = ArrayAdapter.createFromResource(PropertyActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        cooperativeAppSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        cooperativeAppSpinner.setAdapter(cooperativeAppSpinnerAdapter);

        neighbourFeedSpinnerAdapter = ArrayAdapter.createFromResource(PropertyActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        neighbourFeedSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        neighbourFeedSpinner.setAdapter(neighbourFeedSpinnerAdapter);

        locTypeSpinnerAdapter = ArrayAdapter.createFromResource(PropertyActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        locTypeSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        locTypeSpinner.setAdapter(locTypeSpinnerAdapter);

        ambienceSpinnerAdapter = ArrayAdapter.createFromResource(PropertyActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        ambienceSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ambienceSpinner.setAdapter(ambienceSpinnerAdapter);

        appStaySpinnerAdapter = ArrayAdapter.createFromResource(PropertyActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        appStaySpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        appStaySpinner.setAdapter(appStaySpinnerAdapter);

        vehicalSeenSpinnerAdapter= ArrayAdapter.createFromResource(PropertyActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        vehicalSeenSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        vehicalSeenSpinner.setAdapter(vehicalSeenSpinnerAdapter);

        politicalLinkSpinnerAdapter = ArrayAdapter.createFromResource(PropertyActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        politicalLinkSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        politicalLinkSpinner.setAdapter(politicalLinkSpinnerAdapter);

        OverallStatusSpinnerAdapter = ArrayAdapter.createFromResource(PropertyActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        OverallStatusSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        OverallStatusSpinner.setAdapter(OverallStatusSpinnerAdapter);

        reasonNegativeSpinnerAdapter = ArrayAdapter.createFromResource(PropertyActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        reasonNegativeSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        reasonNegativeSpinner.setAdapter(reasonNegativeSpinnerAdapter);


        /**
         * END SETTING ADAPTERS
         */

        /**
         * LOCATION BUTTON
         */

        /**
         * LOCATION END
         */

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                         seaseToLocSpinner = easeToLocSpinner.getSelectedItem().toString();
                        srelationshipSpinner = relationshipSpinner.getSelectedItem().toString();
                        spropTypeSpinner = propTypeSpinner.getSelectedItem().toString();
                        snoYearsPresentOwnSpinner = noYearsPresentOwnSpinner.getSelectedItem().toString();
                        scooperativeAppSpinner = cooperativeAppSpinner.getSelectedItem().toString();
                        sneighbourFeedSpinner = neighbourFeedSpinner.getSelectedItem().toString();
                        slocTypeSpinner = locTypeSpinner.getSelectedItem().toString();
                        sambienceSpinner = ambienceSpinner.getSelectedItem().toString();
                        sappStaySpinner = appStaySpinner.getSelectedItem().toString();
                        svehicalSeenSpinner = vehicalSeenSpinner.getSelectedItem().toString();
                        spoliticalLinkSpinner = politicalLinkSpinner.getSelectedItem().toString();
                        sOverallStatusSpinner = OverallStatusSpinner.getSelectedItem().toString();
                        sreasonNegativeSpinner = reasonNegativeSpinner.getSelectedItem().toString();


                /**
                 * END OF ADAPTERS
                 */

               //  editTExt wala backa hai




                slati = lat.getText().toString().trim();
                slongi = lng.getText().toString().trim();

                /**
                 * EDIT TEXTS END
                 */

                /**
                 * RETROFIT MAGIC
                 */



            }
        });



    }
}

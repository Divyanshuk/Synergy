package com.example.alihasan.synergytwo.Assignments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alihasan.synergytwo.PhotoActivity;
import com.example.alihasan.synergytwo.R;
import com.example.alihasan.synergytwo.api.service.Client;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmploymentActivity extends AppCompatActivity {

    static String SERVER_URL = "http://87408ed5.ngrok.io/project/aztekgo/android/";

    /**
     * Total 30
     * EditText  16
     * Spinner 14
     */

    EditText caseNo;

    EditText companyName, landmark, designOfApp, personMet, designOfPersonMet, personContactNo, officeTele, dateOfJoin, noOfEmp,
            personContacted, desigPersonContacted, nameOfRepManager, contactNoOfRepoManager, salary, tpcPersonName;

    Spinner easeLocSpinner, locTypeSpinner, addConfirmSpinner, doesAppWorkSpinner, officeNameBoardSpinner, orgTypeSpinner,
            visitingCardObtSpinner, natureBusinessSpinner, jobTypeSpinner, workingAsSpinner, jobTransferSpinner, tpcConfirmSpinner,
            overallStatusSpinner, reasonNegativeSpinner;

    /**
     * EDITTEXT AND SPINNER
     * STRING VALUES
     */

    String scompanyName, slandmark, sdesignOfApp, spersonMet, sdesignOfPersonMet, spersonContactNo, sofficeTele, sdateOfJoin, snoOfEmp,
            spersonContacted, sdesgPersonContacted, snameOfRepManager, scontactNoOfRepoManager, ssalary, stpcPersonName;

    String seaseLocSpinner, slocTypeSpinner, saddConfirmSpinner, sdoesAppWorkSpinner, sofficeNameBoardSpinner, sorgTypeSpinner,
            svisitingCardObtSpinner, sNatureBusinessSpinner, sjobTypeSpinner, sworkingAsSpinner, sjobTransferSpinner, stpcConfirmSpinner,
            soverallStatusSpinner, sreasonNegativeSpinner;

    /**
     * SPINNER ADAPTER
     */

    ArrayAdapter<CharSequence> seaseLocSpinnerAdapter, slocTypeSpinnerAdapter, saddConfirmSpinnerAdapter, sdoesAppWorkSpinnerAdapter,
            sofficeNameBoardSpinnerAdapter, sorgTypeSpinnerAdapter,
            svisitingCardObtSpinnerAdapter, sNatureBusinessSpinnerAdapter, sjobTypeSpinnerAdapter, sworkingAsSpinnerAdapter,
            sjobTransferSpinnerAdapter, stpcConfirmSpinnerAdapter,
            soverallStatusSpinnerAdapter, sreasonNegativeSpinnerAdapter;

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


    String BUSINESS_ACTIVITY = "EMPLOYMENT";

    String TABLENAME = "cases-employment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employment);

        SharedPreferences loginData = getSharedPreferences("PDANO", Context.MODE_PRIVATE);
        userName = loginData.getString("PDANO", "");

        SharedPreferences caseData = getSharedPreferences("CASEDATA", Context.MODE_PRIVATE);
        StringCaseNo = caseData.getString("CASENO", "");


        //EditText
        companyName = findViewById(R.id.compName);
        landmark = findViewById(R.id.landmark);
        designOfApp = findViewById(R.id.designation);
        personMet = findViewById(R.id.personMet);
        designOfPersonMet = findViewById(R.id.desigPersonMet);
        personContactNo = findViewById(R.id.personContactNo);
        officeTele = findViewById(R.id.officeTele);
        dateOfJoin = findViewById(R.id.dateOfJoin);
        noOfEmp = findViewById(R.id.empNo);
        personContacted = findViewById(R.id.personContacted);
        desigPersonContacted = findViewById(R.id.desigPersonContacted);
        nameOfRepManager = findViewById(R.id.nameReportManager);
        contactNoOfRepoManager = findViewById(R.id.contactNoRepoManager);
        salary = findViewById(R.id.salary);
        tpcPersonName = findViewById(R.id.tpcPersonName);

        //Spinner
        easeLocSpinner = findViewById(R.id.easeLocSpinner);
        locTypeSpinner = findViewById(R.id.localityTypeSpinner);
        addConfirmSpinner = findViewById(R.id.addressConfirmSpinner);
        doesAppWorkSpinner = findViewById(R.id.doesApplicantWorkSpinner);
        officeNameBoardSpinner = findViewById(R.id.officeNameBoardSpinner);
        orgTypeSpinner = findViewById(R.id.orgTypeSpinner);
        visitingCardObtSpinner = findViewById(R.id.visCardSpinner);
        natureBusinessSpinner = findViewById(R.id.businessNatureSpinner);
        jobTypeSpinner = findViewById(R.id.jobTypeSpinner);
        workingAsSpinner = findViewById(R.id.workingAsSpinner);
        jobTransferSpinner = findViewById(R.id.jobTransferSpinner);
        tpcConfirmSpinner = findViewById(R.id.tpcConfirmSpinner);
        overallStatusSpinner = findViewById(R.id.overallStatusSpinner);
        reasonNegativeSpinner = findViewById(R.id.reasonNegativeSpinner);

        nextButton = findViewById(R.id.nextButton);
        locationButton = findViewById(R.id.locationButton);

        lat = findViewById(R.id.lat);
        lng = findViewById(R.id.lng);


        /**
         * SETTING SPINNER ADAPTERS
         */

        //seaseLocSpinnerAdapter, slocTypeSpinnerAdapter, saddConfirmSpinnerAdapter, sdoesAppWorkSpinnerAdapter,
        //            sofficeNameBoardSpinnerAdapter, sorgTypeSpinnerAdapter,
        //            svisitingCardObtSpinnerAdapter, sNatureBusinessSpinnerAdapter, sjobTypeSpinnerAdapter, sworkingAsSpinnerAdapter,
        //            sjobTransferSpinnerAdapter, stpcConfirmSpinnerAdapter,
        //            soverallStatusSpinnerAdapter, sreasonNegativeSpinnerAdapter;

        seaseLocSpinnerAdapter = ArrayAdapter.createFromResource(EmploymentActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        seaseLocSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        easeLocSpinner.setAdapter(seaseLocSpinnerAdapter);

        slocTypeSpinnerAdapter = ArrayAdapter.createFromResource(EmploymentActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        slocTypeSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        locTypeSpinner.setAdapter(slocTypeSpinnerAdapter);

        saddConfirmSpinnerAdapter = ArrayAdapter.createFromResource(EmploymentActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        saddConfirmSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        addConfirmSpinner.setAdapter(saddConfirmSpinnerAdapter);

        sdoesAppWorkSpinnerAdapter = ArrayAdapter.createFromResource(EmploymentActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        sdoesAppWorkSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        doesAppWorkSpinner.setAdapter(sdoesAppWorkSpinnerAdapter);

        sofficeNameBoardSpinnerAdapter = ArrayAdapter.createFromResource(EmploymentActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        sofficeNameBoardSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        officeNameBoardSpinner.setAdapter(sofficeNameBoardSpinnerAdapter);

        sorgTypeSpinnerAdapter = ArrayAdapter.createFromResource(EmploymentActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        sorgTypeSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        orgTypeSpinner.setAdapter(sorgTypeSpinnerAdapter);

        svisitingCardObtSpinnerAdapter = ArrayAdapter.createFromResource(EmploymentActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        svisitingCardObtSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        visitingCardObtSpinner.setAdapter(svisitingCardObtSpinnerAdapter);

        sNatureBusinessSpinnerAdapter = ArrayAdapter.createFromResource(EmploymentActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        sNatureBusinessSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        natureBusinessSpinner.setAdapter(sNatureBusinessSpinnerAdapter);

        sjobTypeSpinnerAdapter = ArrayAdapter.createFromResource(EmploymentActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        sjobTypeSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        jobTransferSpinner.setAdapter(sjobTypeSpinnerAdapter);

        sworkingAsSpinnerAdapter = ArrayAdapter.createFromResource(EmploymentActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        sworkingAsSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        workingAsSpinner.setAdapter(sworkingAsSpinnerAdapter);

        sjobTransferSpinnerAdapter = ArrayAdapter.createFromResource(EmploymentActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        sjobTransferSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        jobTransferSpinner.setAdapter(sjobTransferSpinnerAdapter);

        stpcConfirmSpinnerAdapter = ArrayAdapter.createFromResource(EmploymentActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        stpcConfirmSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        tpcConfirmSpinner.setAdapter(stpcConfirmSpinnerAdapter);

        soverallStatusSpinnerAdapter = ArrayAdapter.createFromResource(EmploymentActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        soverallStatusSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        overallStatusSpinner.setAdapter(soverallStatusSpinnerAdapter);

        sreasonNegativeSpinnerAdapter = ArrayAdapter.createFromResource(EmploymentActivity.this, R.array., R.layout.support_simple_spinner_dropdown_item);
        sreasonNegativeSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        reasonNegativeSpinner.setAdapter(sreasonNegativeSpinnerAdapter);


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

                seaseLocSpinner = easeLocSpinner.getSelectedItem().toString();
                slocTypeSpinner = locTypeSpinner.getSelectedItem().toString();
                saddConfirmSpinner = addConfirmSpinner.getSelectedItem().toString();
                sdoesAppWorkSpinner = doesAppWorkSpinner.getSelectedItem().toString();
                sofficeNameBoardSpinner = officeNameBoardSpinner.getSelectedItem().toString();
                sorgTypeSpinner = orgTypeSpinner.getSelectedItem().toString();
                svisitingCardObtSpinner = visitingCardObtSpinner.getSelectedItem().toString();
                sNatureBusinessSpinner = natureBusinessSpinner.getSelectedItem().toString();
                sjobTypeSpinner = jobTypeSpinner.getSelectedItem().toString();
                sworkingAsSpinner = workingAsSpinner.getSelectedItem().toString();
                sjobTransferSpinner = jobTransferSpinner.getSelectedItem().toString();
                stpcConfirmSpinner = tpcConfirmSpinner.getSelectedItem().toString();
                soverallStatusSpinner = overallStatusSpinner.getSelectedItem().toString();
                sreasonNegativeSpinner = reasonNegativeSpinner.getSelectedItem().toString();

                /**
                 * END OF ADAPTERS
                 */

                scompanyName = companyName.getText().toString().trim();
                slandmark = landmark.getText().toString().trim();
                sdesignOfApp = designOfApp.getText().toString().trim();
                spersonMet = personMet.getText().toString().trim();
                sdesignOfPersonMet = designOfPersonMet.getText().toString().trim();
                spersonContactNo = personContactNo.getText().toString().trim();
                sofficeTele = officeTele.getText().toString().trim();
                sdateOfJoin = dateOfJoin.getText().toString().trim();
                snoOfEmp = noOfEmp.getText().toString().trim();
                spersonContacted = personContacted.getText().toString().trim();
                sdesgPersonContacted = desigPersonContacted.getText().toString().trim();
                snameOfRepManager = nameOfRepManager.getText().toString().trim();
                scontactNoOfRepoManager = contactNoOfRepoManager.getText().toString().trim();
                ssalary = salary.getText().toString().trim();
                stpcPersonName = tpcPersonName.getText().toString().trim();

                slati = lat.getText().toString().trim();
                slongi = lng.getText().toString().trim();

                /**
                 * EDIT TEXTS END
                 */

                //String stypeCompany,svcard,snameboard,sambience,
                //            sexterior,seaseToLoc,sbact,srecomm;
                /**
                 * RETROFIT MAGIC
                 */

//                retroFitHelper(TABLENAME, seaseLocSpinner,scompanyName, slocTypeSpinner, saddConfirmSpinner,slandmark, sdesignOfApp, spersonMet, sdesignOfPersonMet, spersonContactNo, sdoesAppWorkSpinner, sofficeTele, sofficeNameBoardSpinner, sorgTypeSpinner,
//                        sdateOfJoin, svisitingCardObtSpinner, sNatureBusinessSpinner, sjobTypeSpinner, sworkingAsSpinner, sjobTransferSpinner, snoOfEmp,  spersonContacted, sdesignOfPersonMet1, snameOfRepManager, scontactNoOfRepoManager, ssalary, stpcConfirmSpinner,
//                        stpcPersonName ,soverallStatusSpinner, sreasonNegativeSpinner);

            }
        });

    }

}
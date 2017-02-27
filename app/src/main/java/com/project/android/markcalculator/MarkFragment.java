package com.project.android.markcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;



public class MarkFragment extends Fragment {
    private Mark mMark;
    private EditText mSem;
    private EditText mSubjectamt;
    private Button mDateButton;
    private TextView mDateAdded;
    public static TextView mGpa;
    private Button mProceed;

    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    private static final String ARG_MARK_ID="mark_id";

    public static MarkFragment newInstance(UUID markId){
        Bundle args=new Bundle();
        args.putSerializable(ARG_MARK_ID, markId);

        MarkFragment fragment = new MarkFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //retrieving passed data from marklistfragment -> markactivity -> here
        //getserializable to pull UUID out into a variable, now markfragment got the mark, get it from marklab
        //UUID markId = (UUID)getActivity().getIntent().getSerializableExtra(MarkActivity.EXTRA_MARK_ID);
        UUID markId=(UUID)getArguments().getSerializable(ARG_MARK_ID);

        mMark=MarkLab.get(getActivity()).getMark(markId);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_mark, container, false);

        mSem = (EditText)v.findViewById(R.id.mark_sem);
        //fetched data, set it here
        mSem.setText(mMark.getSem());
        mSem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mMark.setSem(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSubjectamt = (EditText)v.findViewById(R.id.mark_subjectamt);
        //fetched data, set it here
        mSubjectamt.setText(""+mMark.getSubjectamt());
        mSubjectamt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    mMark.setSubjectamt(Integer.parseInt(s.toString()));
                }catch (NumberFormatException e){
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton = (Button)v.findViewById(R.id.mark_date);
        CharSequence formattedDate = DateFormat.format("MMMM yyyy", mMark.getDate());
        mDateButton.setText(formattedDate);
        mDateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                //telling picker what date
                DatePickerFragment dialog = DatePickerFragment.newInstance(mMark.getDate());
                dialog.setTargetFragment(MarkFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mGpa = (TextView)v.findViewById(R.id.mark_gpa);
        mGpa.setText(mMark.getGpa());
        mGpa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mMark.setGpa(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDateAdded = (TextView)v.findViewById(R.id.mark_date_added);
        CharSequence formattedDateAdded = DateFormat.format("EEE, d-MM-yyyy", Calendar.getInstance());
        mDateAdded.setText("Date Added : \n" + formattedDateAdded);

        mProceed = (Button)v.findViewById(R.id.mark_proceed);
        mProceed.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = MarkCalculateActivity.newIntent(getActivity(), mMark.getId());
                startActivity(intent);
            }
        });

        return v;
    }

    //retrieve extra and set date, refresh text of button
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode!= Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mMark.setDate(date);
            CharSequence formattedDate = DateFormat.format("EEE, d-MM-yyyy", mMark.getDate());
            mDateButton.setText(formattedDate);
        }
    }

    //menu
    //menu add
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_mark, menu);
    }

    //responding to menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menu_item_delete_mark:
                MarkLab.get(getActivity()).deleteMark(mMark);
                getActivity().finish();
                Toast.makeText(getActivity(),mMark.getSem()+" removed", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_item_share_mark:
                Toast.makeText(getActivity(),getMarkReport(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, getMarkReport());
                i = Intent.createChooser(i, getString(R.string.send_report));
                startActivity(i);
                return true;
            case R.id.menu_item_achievement:
                Intent intent = MarkAchievementActivity.newIntent(getActivity(), mMark.getId());
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String getMarkReport(){

        String sem = mMark.getSem();

        String dateFormat = "MMMM yyyy";
        String dateString = DateFormat.format(dateFormat, mMark.getDate()).toString();

        String gpa = mMark.getGpa();

        String report = getString(R.string.mark_report, sem, dateString, gpa);

        return report;
    }

    @Override
    public void onPause(){
        super.onPause();
        MarkLab.get(getActivity()).updateMark(mMark);
    }
}

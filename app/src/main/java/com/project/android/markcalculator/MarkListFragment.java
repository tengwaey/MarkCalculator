package com.project.android.markcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MarkListFragment extends Fragment {

    private RecyclerView mMarkRecyclerView;
    private MarkAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_mark_list, container, false);

        //creating recycler view
        mMarkRecyclerView = (RecyclerView) view.findViewById(R.id.mark_recycler_view);
        mMarkRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    //refreshing recycler view when things changed
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    //added stuff for refreshing recycler view
    private void updateUI(){
        MarkLab markLab = MarkLab.get(getActivity());
        List<Mark>marks = markLab.getMarks();

        if(mAdapter==null) {
            mAdapter = new MarkAdapter(marks);
            mMarkRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.setMarks(marks);
            mAdapter.notifyDataSetChanged();
        }
    }

    //View Holder displaying, 2 stuff now
    private class MarkHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //now display more stuff
        private TextView mSemTextView;
        private TextView mDateTextView;
        private TextView mGpaTextView;

        private Mark mMark;

        //additional add here
        public MarkHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            mSemTextView=(TextView)itemView.findViewById(R.id.list_item_mark_sem_text_view);
            mDateTextView=(TextView)itemView.findViewById(R.id.list_item_mark_date_text_view);
            mGpaTextView=(TextView)itemView.findViewById(R.id.list_item_mark_gpa_text_view);
        }

        //additional display in list adds here
        public void bindMark(Mark mark){
            mMark = mark;

            mSemTextView.setText("Semester " + mMark.getSem());
            CharSequence formattedDate = DateFormat.format("MMMM yyyy", mMark.getDate());
            mDateTextView.setText(formattedDate);
            mGpaTextView.setText(mMark.getGpa());
        }

        //onclick for each item
        @Override
        public void onClick(View v){
            Toast.makeText(getActivity(),"Semester " + mMark.getSem() + " selected", Toast.LENGTH_SHORT).show();

            //starting Next activity class, passing ID to markActivity
            Intent intent = MarkActivity.newIntent(getActivity(), mMark.getId());
            startActivity(intent);
        }
    }

    //adapter connecting view holder and Mark details
    private class MarkAdapter extends RecyclerView.Adapter<MarkHolder> {
        private List<Mark> mMarks;

        public MarkAdapter(List<Mark> marks) {
            mMarks = marks;
        }

        //called by recyclerview, create and wrap in viewholder
        @Override
        public MarkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_mark, parent, false);
            return new MarkHolder(view);
        }

        //bind marks to view by sending sem
        @Override
        public void onBindViewHolder(MarkHolder holder, int position) {
            Mark mark = mMarks.get(position);
            holder.bindMark(mark);
        }


        @Override
        public int getItemCount() {
            return mMarks.size();
        }

        public void setMarks(List<Mark> marks){
            mMarks = marks;
        }
    }

    //menu add
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_mark_list, menu);
    }

    //menu setting enable
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    //responding to menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menu_item_new_mark:
                Mark mark = new Mark();
                MarkLab.get(getActivity()).addMark(mark);
                Intent intent = MarkActivity.newIntent(getActivity(), mark.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

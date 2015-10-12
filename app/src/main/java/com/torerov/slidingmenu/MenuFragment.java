package com.torerov.slidingmenu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuFragment extends Fragment {
    public MenuFragment() {
        // Required empty public constructor
    }

    public static final int MENU_MAIN = 0;
    public static final int MENU_ONE = 1;
    public static final int MENU_TWO = 2;

    public static class Item {
        public String menuName;
        public int menuId;
        public Item(String name, int id) {
            menuName = name;
            menuId = id;
        }
        @Override
        public String toString() {
            return menuName;
        }
    }
    private OnMenuSelectListener mListener;
    ListView listView;
    ArrayAdapter<Item> mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ArrayAdapter<Item>(getContext (), android.R.layout.simple_list_item_1);
        initData();
    }

    private void initData() {
        mAdapter.add(new Item("Main", MENU_MAIN));
        mAdapter.add(new Item("One", MENU_ONE));
        mAdapter.add(new Item("Two", MENU_TWO));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_menu, container, false);
        listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mListener != null){
                    mListener.onMenuSelected((Item)listView.getItemAtPosition(position));
                }
            }
        });
        return view;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            mListener = (OnMenuSelectListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnMenuSelectListener {
        // TODO: Update argument type and name
        public void onMenuSelected(Item item);
    }

}

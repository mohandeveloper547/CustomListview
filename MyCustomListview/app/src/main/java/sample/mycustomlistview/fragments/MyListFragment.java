package sample.mycustomlistview.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import sample.mycustomlistview.adapters.CustomListAdapter;
import sample.mycustomlistview.common.Parser;
import sample.mycustomlistview.common.URLConstants;
import sample.mycustomlistview.dto.CustomDTO;
import sample.mycustomlistview.dto.SimpleDTO;
import sample.mycustomlistview.main.MainActivity;
import sample.mycustomlistview.main.R;
import sample.mycustomlistview.utils.CustomProgress;
import sample.mycustomlistview.utils.WebServiceCalls;

/**
 * Created by Shyam.Yammanuru on 4/29/2016.
 */
public class MyListFragment extends ListFragment {
    CustomListAdapter adapter;
    ArrayList<SimpleDTO> rowList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new GetCustomListTask(getActivity()).execute();
        adapter = new CustomListAdapter(getActivity().getLayoutInflater(), ImageLoader.getInstance(), rowList);
        setListAdapter(adapter);

    }
    public void refreshList()
    {
         new GetCustomListTask(getActivity()).execute();
    }

    class GetCustomListTask extends AsyncTask<String, Void, String> implements DialogInterface.OnCancelListener {
        CustomProgress mProgressHUD;
        Context ctx;
        public GetCustomListTask(Context ctx) {
            this.ctx = ctx;
        }
        @Override
        public void onCancel(DialogInterface dialog) {
            this.cancel(true);
            mProgressHUD.dismiss();
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressHUD = CustomProgress.show(ctx, getResources().getString(R.string.loading_msg), true, true, this);
        }
        @Override
        protected String doInBackground(String... params) {
            String url = URLConstants.JSON_URL;
            return WebServiceCalls.getData(url);
        }
        @Override
        protected void onPostExecute(String res) {
            super.onPostExecute(res);
            mProgressHUD.dismiss();
            CustomDTO dto = Parser.getCustomList(res);
            if(dto != null)
            {
                rowList = dto.getInfoList();
                adapter.updateList(rowList);
                // for setting up the action bar title.
                ((MainActivity) getActivity()).setActionBarTitle(dto.getListTitle());

            }
        }
    }
}

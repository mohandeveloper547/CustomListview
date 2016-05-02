package sample.customlistview.main;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import sample.customlistview.adapters.CustomListAdapter;
import sample.customlistview.asynctasks.GetCustomListTask;
import sample.customlistview.common.CallBack;
import sample.customlistview.dto.CustomDTO;
import sample.customlistview.dto.SimpleDTO;

public class MainActivity extends AppCompatActivity {
    ListView customListView;
    CustomListAdapter adapter;
    ArrayList<SimpleDTO> rowList;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();

        adapter = new CustomListAdapter(getLayoutInflater(), ImageLoader.getInstance(), rowList);
        customListView = (ListView)findViewById(R.id.customListView);
        customListView.setAdapter(adapter);
        new GetCustomListTask(MainActivity.this, new GetListTaskCallBack()).execute();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }
    /**
     * On selecting action bar icons
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_refresh:
                // refresh
                new GetCustomListTask(MainActivity.this, new GetListTaskCallBack()).execute();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
/*
* CallBack class for the server response.
* */
    class GetListTaskCallBack implements CallBack{

        @Override
        public void onComplete(Object obj) {
            if(obj != null && obj instanceof CustomDTO)
            {
                CustomDTO dto = (CustomDTO)obj;
                rowList = dto.getInfoList();
                adapter.updateList(rowList);
                actionBar.setTitle(dto.getListTitle());
            }
            else{

            }
        }
    }
}

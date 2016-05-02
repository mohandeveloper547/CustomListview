package sample.customlistview.asynctasks;

/**
 * Created by shyam.yammanuru on 21/04/2016.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;

import sample.customlistview.common.CallBack;
import sample.customlistview.common.Parser;
import sample.customlistview.common.URLConstants;
import sample.customlistview.utils.CustomProgress;
import sample.customlistview.utils.WebServiceCalls;

public class GetCustomListTask extends AsyncTask<String, Void, String> implements OnCancelListener {
    CustomProgress mProgressHUD;
    CallBack callBack;
    Context ctx;

    public GetCustomListTask(Context ctx, CallBack callBack) {
        this.ctx = ctx;
        this.callBack = callBack;
      //  System.out.println("GetCustomListTask <=========> GetCustomListTask");
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        this.cancel(true);
        mProgressHUD.dismiss();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressHUD = CustomProgress.show(ctx, "Please wait...", true, true, this);
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
        callBack.onComplete(Parser.getCustomList(res));
   //     System.out.println("Result : =============> " + res);
    }
}

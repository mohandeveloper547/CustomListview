package sample.mycustomlistview.common;

/**
 * Created by shyam.yammanuru on 21/04/2016.
 */

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import sample.mycustomlistview.dto.CustomDTO;
import sample.mycustomlistview.dto.SimpleDTO;


public class Parser {
	private static String KEY_TITLE = "title";
	private static String KEY_ROWS = "rows";
	private static String KEY_DESCRIPTION = "description";
	private static String KEY_IMAGEURL = "imageHref";

	public static CustomDTO getCustomList(String jsonString) {
		CustomDTO customDTO = new CustomDTO();

		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			if (jsonObject != null) {
				String listTitle = jsonObject.getString(KEY_TITLE);
				customDTO.setListTitle(listTitle);
				ArrayList<SimpleDTO> list = new ArrayList<SimpleDTO>();
				JSONArray jsonArray = jsonObject.getJSONArray(KEY_ROWS);
				for(int i=0;i<jsonArray.length();i++)
				{
					SimpleDTO dto = new SimpleDTO();
					JSONObject jobj = jsonArray.getJSONObject(i);
					dto.setTitle(jobj.optString(KEY_TITLE));
					dto.setDescription(jobj.optString(KEY_DESCRIPTION));
					dto.setImageUrl(jobj.optString(KEY_IMAGEURL));
					list.add(dto);

				}
				customDTO.setInfoList(list);
			}
		}
		catch(Exception e)
		{

		}
		return customDTO;
	}
}

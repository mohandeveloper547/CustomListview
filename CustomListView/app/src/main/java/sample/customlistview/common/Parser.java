package sample.customlistview.common;

/**
 * Created by shyam.yammanuru on 21/04/2016.
 */

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import sample.customlistview.dto.CustomDTO;
import sample.customlistview.dto.SimpleDTO;

public class Parser {

	public static CustomDTO getCustomList(String jsonString) {
		CustomDTO customDTO = new CustomDTO();

		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			if (jsonObject != null) {
				String listTitle = jsonObject.getString("title");
				customDTO.setListTitle(listTitle);
				ArrayList<SimpleDTO> list = new ArrayList<SimpleDTO>();
				JSONArray jsonArray = jsonObject.getJSONArray("rows");
				for(int i=0;i<jsonArray.length();i++)
				{
					SimpleDTO dto = new SimpleDTO();
					JSONObject jobj = jsonArray.getJSONObject(i);
					dto.setTitle(jobj.optString("title"));
					dto.setDescription(jobj.optString("description"));
					dto.setImageUrl(jobj.optString("imageHref"));
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

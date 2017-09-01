package online.a2brain.trycamera2;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraAccessException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static online.a2brain.trycamera2.R.id.listView;

public class MainActivity extends AppCompatActivity {

    //private CameraManager mCameraManager;
    private final String Tag="TryCamera2)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ListView listView = (ListView) this.findViewById(R.id.listView);

        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

        //get the Camera2
        CameraManager manager =
                (CameraManager)getSystemService(CAMERA_SERVICE);
        try {

            for (String cameraId : manager.getCameraIdList()) {

                Log.d(Tag,"find cameraID"+cameraId);
                CameraCharacteristics chars
                        = manager.getCameraCharacteristics(cameraId);
                // Do something with the characteristics

               // List<CameraCharacteristics.Key<?>> list=chars.getKeys();

            for (CameraCharacteristics.Key<?> key:chars.getKeys()){

                Log.d(Tag, "cameraID"+cameraId+" find key:"+key.getName()+"----"+"value:"+chars.get(key));

                Map<String, Object> item = new HashMap<String, Object>();
                item.put("cameraId",cameraId);
                item.put("key",key.getName());
                item.put("value",chars.get(key));

                dataList.add(item);

                }

            }
        }


            catch (CameraAccessException e) {
                e.printStackTrace();
            }

        //创建SimpleAdapter适配器将数据绑定到item显示控件上
        SimpleAdapter adapter = new SimpleAdapter(this, dataList, R.layout.item,
                new String[]{"cameraId", "key", "value"}, new int[]{R.id.cameraId, R.id.key, R.id.value});
        //实现列表的显示
        listView.setAdapter(adapter);


    }
}

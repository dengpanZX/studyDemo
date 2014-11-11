package com.zxxk.kg.listview_test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zxxk.kg.listview_test.listview.MyListView;
import com.zxxk.kg.listview_test.listview.MyListView.OnRefreshListener;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * ListView��ҳ��������
 * @author 
 *
 */
public class ListViewPull_DownActivity extends Activity {
	
    private MyListView listView;
    private List<Map<String,Object>> data;
    private listViewAdapter adapter;
    //��ҳ���ص����ݵ�����
    private int pageSize=10;
    private final int pageType=1;
    //�鿴����
    private TextView moreTextView;
    //���ڼ��ؽ�����
    private LinearLayout loadProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pull_down);
        
        listView=(MyListView)findViewById(R.mainactivity.freelook_listview);
        
        //��һ��������1��ʼ��  �ڶ�����������ʾ����Ŀ
        data=InitValue.initValue(1,15);
        
        //��ListView�����"���ظ���"
        addPageMore();
        //���"���ظ���"һ��Ҫ������Adapter֮ǰ
        adapter=new listViewAdapter();
        listView.setAdapter(adapter);
        
        listView.setonRefreshListener(new OnRefreshListener() {
    		public void onRefresh() {
    			new AsyncTask<Void, Void, Void>() {
    				protected Void doInBackground(Void... params) {
    					try {
    						Thread.sleep(1000);
    					} catch (Exception e) {
    						e.printStackTrace();
    					}
    					
    					data = InitValue.initValue(1,data.size(),data);
//    					data.add("ˢ�º���ӵ�����");
//    					data.add("ˢ�º���ӵ�����");
//    					data.add("ˢ�º���ӵ�����");
//    					data.add("ˢ�º���ӵ�����");
//    					data.add("ˢ�º���ӵ�����");
    					return null;
    				}

    				@Override
    				protected void onPostExecute(Void result) {
    					adapter.notifyDataSetChanged();
    					listView.onRefreshComplete();
    				}

    			}.execute(null,null);
    		}
    	});
    }
    
    private class listViewAdapter extends BaseAdapter{
        int count=data.size();
        @Override
        public int getCount() {
            return count;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=LayoutInflater.from(ListViewPull_DownActivity.this).inflate(R.layout.item_new, null);
            TextView title=(TextView)view.findViewById(R.main_list.tv1);
            TextView text=(TextView)view.findViewById(R.main_list.tv2);
            title.setText(data.get(position).get("title").toString());
            text.setText(data.get(position).get("text").toString());
            return view;
        }
        
    }
    
    /**
     * ������һҳ������
     * @param pageStart
     * @param pageSize
     */
    private void chageListView(int pageStart,int pageSize){
        List<Map<String,Object>> data=InitValue.initValue(pageStart,pageSize);
        for (Map<String, Object> map : data) {
            this.data.add(map);
        }
        data=null;
    }
    
    /**
     * ��ListView�����"���ظ���"
     */
    private void addPageMore(){
        View view=LayoutInflater.from(this).inflate(R.layout.list_page_load, null);
        moreTextView=(TextView)view.findViewById(R.id.more_id);
        loadProgressBar=(LinearLayout)view.findViewById(R.id.load_id);
        moreTextView.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //����"���ظ���"
                moreTextView.setVisibility(View.GONE);
                //��ʾ������
                loadProgressBar.setVisibility(View.VISIBLE);
                
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //����3�룬����ģ���������ʱ��
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        
                        //����ģ�����ݣ���һҳ���ݣ� ����������£�����������ǲ���Ҫ��ֱ��ʹ����������������������
                        chageListView(data.size()+1,pageSize);
                        
                        Message mes=handler.obtainMessage(pageType);
                        handler.sendMessage(mes);
                    }
                }).start();
            }
        });
        listView.addFooterView(view);
    }
    
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case pageType:
                //�ı�����������Ŀ
                adapter.count += pageSize;
                //֪ͨ�����������ָı����
                adapter.notifyDataSetChanged();
				listView.onRefreshComplete();
                //�ٴ���ʾ"���ظ���"
                moreTextView.setVisibility(View.VISIBLE);
                //�ٴ����ء���������
                loadProgressBar.setVisibility(View.GONE);
                break;

            default:
                break;
            }
            
            super.handleMessage(msg);
        }
    };
}
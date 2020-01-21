package workshop.expandablelist;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpListAdapter extends BaseExpandableListAdapter {
	private Context context;		//�O���ϥ�CollegeListAdapter���餧Activity.Context
	private ArrayList<String> groups;
	private ArrayList<ArrayList<String>> childs;
	public ExpListAdapter(Context context, ArrayList<String> groups, ArrayList<ArrayList<String>> childs) {
		this.context = context;
		this.groups = groups;
		this.childs = childs;
	}

	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childs.get(groupPosition).get(childPosition);
	}

	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		String department =  childs.get(groupPosition).get(childPosition);

		//�إߤl�M��UI����, �ó]�m������������e
		View view = LayoutInflater.from(context).inflate(R.layout.childitem, null);
		TextView dept_name = (TextView) view.findViewById(R.id.text_department);
		dept_name.setText(department);
		ImageView imageView = (ImageView)view.findViewById(R.id.image_go);
		imageView.setImageResource(R.drawable.ic_go);
		return view;	
	}

	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return childs.get(groupPosition).size();
	}

	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return groups.get(groupPosition);
	}

	public int getGroupCount() {
		return groups.size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String college = groups.get(groupPosition);
		//�إ߸s��UI����,�ó]�m������������e
		View view = LayoutInflater.from(context).inflate(R.layout.groupitem, null);
		TextView college_name = (TextView)view.findViewById(R.id.text_college);
		college_name.setText(college);		
		return view;
	}

	public boolean hasStableIds() {
		return false;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;		//�p�G���\�ϥΪ��I��l���ءA�h�Ǧ^true
	}
}

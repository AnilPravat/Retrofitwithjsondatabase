package myapp.anilandroid.com.retrofitwithjsondb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import myapp.anilandroid.com.retrofitwithjsondb.R;
import myapp.anilandroid.com.retrofitwithjsondb.model.Resultdata;

public class Customadapter extends RecyclerView.Adapter<Customadapter.ViewHolder>{

    Context context;

    private List <Resultdata> resultlist;

    public Customadapter(Context context, List<Resultdata> resultlist) {
        this.context = context;
        this.resultlist = resultlist;
    }

    @Override
    public Customadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from( parent.getContext() ).inflate( R.layout.rowitem_data,parent,false );
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Resultdata resultdata=resultlist.get( position );
        holder.Id.setText(String.valueOf(  resultdata.getId()) );
        holder.name.setText( resultdata.getName());
        holder.username.setText( resultdata.getUsername() );
        holder.phone.setText( resultdata.getPhone() );
        holder.website.setText( resultdata.getWebsite() );
        holder.email.setText( resultdata.getEmail() );
        holder.street.setText( resultdata.getAddress().getStreet());
        holder.suite.setText( resultdata.getAddress().getSuite());
        holder.city.setText( resultdata.getAddress().getCity());
        holder.zipcode.setText( resultdata.getAddress().getZipcode());
        holder.lat.setText(resultdata.getAddress().getGeo().getLat());
        holder.lng.setText(resultdata.getAddress().getGeo().getLng());
        holder.cname.setText( resultdata.getCompany().getName());
        holder.catchphrase.setText( resultdata.getCompany().getCatchPhrase() );
        holder.bs.setText( resultdata.getCompany().getBs() );




    }

    @Override
    public int getItemCount() {
        return resultlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView Id,name,username,email,street,suite,city,zipcode,lat,lng,phone,website,cname,catchphrase,bs;
        public ViewHolder(View itemView) {
            super( itemView );

            Id=(TextView) itemView.findViewById( R.id.tv_Id );
            name=(TextView) itemView.findViewById( R.id.tv_Name );
            username=(TextView) itemView.findViewById( R.id.tv_Username );
            email=(TextView) itemView.findViewById( R.id.tv_Email );
            street=(TextView) itemView.findViewById( R.id.tv_Street );
            suite=(TextView) itemView.findViewById( R.id.tv_Suite );
            city=(TextView) itemView.findViewById( R.id.tv_City );
            zipcode=(TextView) itemView.findViewById( R.id.tv_Zipcode );
            lat=(TextView) itemView.findViewById( R.id.tv_Lat );
            lng=(TextView) itemView.findViewById( R.id.tv_Lng );
            phone=(TextView) itemView.findViewById( R.id.tv_Phone );
            website=(TextView) itemView.findViewById( R.id.tv_Website );
            cname=(TextView) itemView.findViewById( R.id.tv_Cname );
            catchphrase=(TextView) itemView.findViewById( R.id.tv_Catchphrase );
            bs=(TextView) itemView.findViewById( R.id.tv_Bs );
        }
    }
}

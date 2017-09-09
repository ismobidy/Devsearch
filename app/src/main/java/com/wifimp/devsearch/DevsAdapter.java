package com.wifimp.devsearch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ismob on 9/8/2017.
 */

public class DevsAdapter extends RecyclerView.Adapter<DevsAdapter.ViewHolder> {


        public static final String KEY_NAME = "name";
        public static final String KEY_IMAGE = "image";
        public static final String KEY_URL = "url";

        private List<DevsList> devsLists;
        private Context context;

        public DevsAdapter(List<DevsList> devsLists, Context context) {

            this.devsLists = devsLists;
            this.context = context;
        }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.devs_list,parent,false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){

        final DevsList devsList = devsLists.get(position);
        holder.login.setText(devsList.getLogin());

        Picasso.with(context)
                .load(devsList.getAvatar_url())
                .into(holder.avatar_url);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {

                                                       DevsList devsList1 = devsLists.get(position);

                                                       Intent ProfileIntent = new Intent(v.getContext(), ProfileActivity.class);
                                                       ProfileIntent.putExtra(KEY_NAME, devsList1.getLogin());
                                                       ProfileIntent.putExtra(KEY_URL, devsList1.getHtml_url());
                                                       ProfileIntent.putExtra(KEY_IMAGE, devsList1.getAvatar_url());

                                                       v.getContext().startActivity(ProfileIntent);


                                                   }
                                               });

    }

    @Override
    public int getItemCount(){
        return devsLists.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView login;
        public ImageView avatar_url;
        public TextView html_url;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView){

            super(itemView);

            login = (TextView) itemView.findViewById(R.id.user_name);
            avatar_url = (ImageView) itemView.findViewById(R.id.image_view);
            html_url = (TextView)itemView.findViewById(R.id.html_url);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linear_layout);
        }
    }
}

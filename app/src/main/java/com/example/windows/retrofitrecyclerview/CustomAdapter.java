package com.example.windows.retrofitrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<UserData> userData;
    private Context context;

    public CustomAdapter(Context context, List<UserData> userData) {
        this.context = context;
        this.userData = userData;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(userData.get(position).getUrl())
                .into(holder.image);

        holder.name.setText(userData.get(position).getName());
        holder.age.setText((userData.get(position).getAge())+" Years");
        holder.location.setText(userData.get(position).getLocation());
        int itemCount = userData.get(position).getDetails().size();
        for(int i=0;i<itemCount;i++){
            holder.details.append("\u2022 "+(userData.get(position).getDetails().get(i))+"\n");
        }

    }

    @Override
    public int getItemCount() {
        return userData.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView name,age,location,details;

        public CustomViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.user_image);
            name = itemView.findViewById(R.id.user_name);
            age = itemView.findViewById(R.id.user_age);
            location = itemView.findViewById(R.id.user_location);
            details = itemView.findViewById(R.id.user_details);

        }
    }

}

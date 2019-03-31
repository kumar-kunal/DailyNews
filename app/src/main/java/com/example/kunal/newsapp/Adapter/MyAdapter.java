package com.example.kunal.newsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kunal.newsapp.Model.News;
import com.example.kunal.newsapp.Utility.NewsDetail;
import com.example.kunal.newsapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{
    ArrayList<News> arrayList;
    Context context;

    public MyAdapter(ArrayList<News> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_view,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        News current=arrayList.get(position);
        holder.title.setText(current.getTitle());
//        holder.desc.setText(current.getDescription());
//        holder.auth.setText(current.getAuthor());
        holder.src.setText(current.getSource().getName());
        Picasso.with(context).load(current.getImageUrl()).into(holder.imageView);

//        holder.time.setText(current.getPublishedAt());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title,auth,desc,src,time;

         public MyHolder(final View itemView) {
             super(itemView);

             imageView=itemView.findViewById(R.id.imageView);
             title=itemView.findViewById(R.id.newsTitle);
//             auth=itemView.findViewById(R.id.author);
//             desc=itemView.findViewById(R.id.description);
             src=itemView.findViewById(R.id.srcname);
//             time=itemView.findViewById(R.id.date);
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     String clickedArticleUrl=arrayList.get(getAdapterPosition()).getUrl();
                     Intent intent = new Intent(context, NewsDetail.class);
                     intent.putExtra("ARTICLE_URL", clickedArticleUrl);
                     context.startActivity(intent);
//                     CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//                     CustomTabsIntent customTabsIntent = builder.build();
//                     customTabsIntent.launchUrl(context, Uri.parse(s));




                 }
             });

         }
     }
}

package com.example.hp.ieeepec;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

// RecyclerView.Adapter: binds the data to the view
// RecyclerView.ViewHolder: holds the view
public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private Context ctx;
    private List<Notification> notificationList;

    public NotificationAdapter(Context ctx, List<Notification> notificationList) {
        this.ctx = ctx;
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    //creates view holder instance and returns it
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.activity_notification_list_layout, null);
        NotificationViewHolder holder = new NotificationViewHolder(view);
        return holder;
    }

    @Override
    //binds data to our view holder
    public void onBindViewHolder(@NonNull NotificationViewHolder notificationViewHolder, int i) {
        Notification notification = notificationList.get(i);
        notificationViewHolder.title.setText(notification.getTitle());
        notificationViewHolder.desc.setText(notification.getDesc());
        notificationViewHolder.date.setText(notification.getDate());
        notificationViewHolder.time.setText(notification.getTime());
        notificationViewHolder.venue.setText(notification.getVenue());
        notificationViewHolder.logo.setImageResource(notification.getLogo());


    }

    @Override
    //returns elements of the list
    public int getItemCount() {
        return notificationList.size();
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {

        ImageView logo;
        TextView title, desc, date, time, venue;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);

            logo = itemView.findViewById(R.id.notification_logo);
            title = itemView.findViewById(R.id.notification_title);
            desc = itemView.findViewById(R.id.notification_desc);
            date = itemView.findViewById(R.id.notification_date);
            time = itemView.findViewById(R.id.notification_time);
            venue = itemView.findViewById(R.id.notification_venue);
        }
    }
}

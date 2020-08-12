package com.belajar.assignmentdatabinding.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Filter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.belajar.assignmentdatabinding.R;
import com.belajar.assignmentdatabinding.models.users.DataItem;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> implements Filterable {
    private List<DataItem> usersList;
    private List<DataItem> usersListFiltered;
    private Context context;

    public void setUsersList(Context context, final List<DataItem> usersList) {
        this.context = context;
        if (this.usersList == null){

            this.usersList = usersList;
            this.usersListFiltered = usersList;
            notifyItemChanged(0, usersListFiltered);
        }else{
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return UserAdapter.this.usersList.size();
                }

                @Override
                public int getNewListSize() {
                    return usersList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return UserAdapter.this.usersList
                            .get(oldItemPosition).getEmail()
                            ==
                            usersList.get(newItemPosition).getEmail();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    DataItem newUsers = UserAdapter.this.usersList.get(oldItemPosition);
                    DataItem oldUsers = usersList.get(newItemPosition);
                    return newUsers.getEmail() == oldUsers.getEmail();
                }
            });

            this.usersList = usersList;
            this.usersListFiltered = usersList;
            result.dispatchUpdatesTo(this);
        }

    }
    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_user,parent,false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {
        holder.name.setText(usersListFiltered.get(position).getFirstName()+ " "+ usersListFiltered.get(position).getLastName());
        holder.email.setText(usersListFiltered.get(position).getEmail());
        Glide.with(context).load(usersListFiltered.get(position).getAvatar())
                .apply(RequestOptions.centerCropTransform()).into(holder.imageUsers);
        holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(usersListFiltered.get(holder.getAdapterPosition())));
    }

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public int getItemCount() {
        if (usersList != null){
            return usersListFiltered.size();
        }else{
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    usersListFiltered = usersList;
                } else {
                    List<DataItem> filteredList = new ArrayList<>();
                    for (DataItem data : usersList) {
                        if (data.getFirstName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(data);
                        }
                    }
                    usersListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = usersListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                usersListFiltered = (ArrayList<DataItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,email;
        ImageView imageUsers;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName_user);
            email = itemView.findViewById(R.id.tvEmail_user);
            imageUsers = itemView.findViewById(R.id.profile_image_user);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(DataItem data);
    }
}

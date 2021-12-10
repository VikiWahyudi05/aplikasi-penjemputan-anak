package com.e_carter.userroles;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder> {

    private static final String Tag = "RecyclerView";
    private Context mContext;
    private ArrayList<Pengantaran> pengantaransArrayList;

    public UserRecyclerAdapter(Context mContext, ArrayList<Pengantaran> pengantaransArrayList) {
        this.mContext = mContext;
        this.pengantaransArrayList = pengantaransArrayList;
    }

    @NonNull
    @Override
    public UserRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.item ,parent,false);

        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecyclerAdapter.ViewHolder holder, final int position) {
        holder.tvNamaDriverr.setText( pengantaransArrayList.get(position).getNamaAnak() );

        Glide.with( mContext )
                .load( pengantaransArrayList.get( position ) .getImageUrl())
                .into( holder.imageView );

        holder.tvWaktuu.setText( pengantaransArrayList.get( position ) .getWaktuJemput());
        holder.tvEmail.setText( pengantaransArrayList.get( position ).getEmail() );
//        pengantaran
        holder.tvNamaAnak.setText( pengantaransArrayList.get( position ).getNamaAnak2() );
        holder.tvWaktuAnak.setText( pengantaransArrayList.get( position ).getWaktuAntar() );
        Glide.with( mContext )
                .load( pengantaransArrayList.get( position ) .getImgAnak())
                .into( holder.imgAnak );

//        holder.itemView.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext.getApplicationContext(),UpdatePostCarter.class);
//                intent.putExtra("id", pengantaransArrayList.get( position ).getId());
//                intent.putExtra("email", pengantaransArrayList.get( position ).getEmail());
//                intent.putExtra("image", pengantaransArrayList.get( position ).getImageUrl());
//                intent.putExtra("waktu", pengantaransArrayList.get( position ).getWaktuJemput());
//                intent.putExtra("namaanak", pengantaransArrayList.get( position ).getNamaAnak());
//                view.getContext().startActivity( intent );
//            }
//        } );
    }

    @Override
    public int getItemCount() {
        return pengantaransArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView,imgAnak;
        TextView tvNamaDriverr,tvWaktuu,tvEmail,tvNamaAnak,tvWaktuAnak;


        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            imageView = itemView.findViewById( R.id.mImgDriver );
            tvNamaDriverr = itemView.findViewById( R.id.mNamaDriver );
            tvWaktuu = itemView.findViewById( R.id.mWaktu );
            tvEmail = itemView.findViewById( R.id.dummyEmail );
            tvNamaAnak = itemView.findViewById( R.id.tvNamaAnakk );
            tvWaktuAnak = itemView.findViewById( R.id.tvWaktuAntarr );
            imgAnak = itemView.findViewById( R.id.mImgAnak );

        }
    }
}

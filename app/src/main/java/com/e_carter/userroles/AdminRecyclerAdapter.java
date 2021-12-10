package com.e_carter.userroles;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdminRecyclerAdapter extends RecyclerView.Adapter<AdminRecyclerAdapter.ViewHolder> {

    private static final String Tag = "RecyclerView";
    private Context mContext;
    private ArrayList<Penjemputan> penjemputansArrayList;

    public AdminRecyclerAdapter(Context mContext, ArrayList<Penjemputan> penjemputansArrayList) {
        this.mContext = mContext;
        this.penjemputansArrayList = penjemputansArrayList;
    }

    @NonNull
    @Override
    public AdminRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.penjemputan_item ,parent,false);

        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull AdminRecyclerAdapter.ViewHolder holder, final int position) {
        holder.tvNamaDriverr.setText( penjemputansArrayList.get(position).getNamaAnak() );

        Glide.with( mContext )
                .load( penjemputansArrayList.get( position ) .getImageUrl())
                .into( holder.imageView );

        holder.tvWaktuu.setText( penjemputansArrayList.get( position ) .getWaktuJemput());
        holder.tvEmail.setText( penjemputansArrayList.get( position ).getEmail() );
//        pengantaran
        holder.tvNamaAnak.setText( penjemputansArrayList.get( position ).getNamaAnak2() );
        holder.tvWaktuAntar.setText( penjemputansArrayList.get( position ).getWaktuAntar() );
        Glide.with( mContext )
                .load( penjemputansArrayList.get( position ) .getImgAnak())
                .into( holder.imgAnak );
        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext.getApplicationContext(),UpdatePostCarter.class);
                intent.putExtra("id", penjemputansArrayList.get( position ).getId());
                intent.putExtra("email", penjemputansArrayList.get( position ).getEmail());
                intent.putExtra("image", penjemputansArrayList.get( position ).getImageUrl());
                intent.putExtra("waktu", penjemputansArrayList.get( position ).getWaktuJemput());
                intent.putExtra("namaanak", penjemputansArrayList.get( position ).getNamaAnak());
                view.getContext().startActivity( intent );
            }
        } );
    }

    @Override
    public int getItemCount() {
        return penjemputansArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView,imgAnak;
        TextView tvNamaDriverr,tvWaktuu,tvEmail;
        TextView tvNamaAnak,tvWaktuAntar;


        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            imageView = itemView.findViewById( R.id.tvImageDriver );
            tvNamaDriverr = itemView.findViewById( R.id.tvNamaDriverr );
            tvWaktuu = itemView.findViewById( R.id.tvWaktuu );
            tvEmail = itemView.findViewById( R.id.tvEmaill );
            imgAnak = itemView.findViewById( R.id.mImgAnak );
            tvNamaAnak = itemView.findViewById( R.id.tvNamaAnakk);
            tvWaktuAntar = itemView.findViewById( R.id.tvWaktuAntarr );
        }
    }
}

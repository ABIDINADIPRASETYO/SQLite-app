package com.example.abidin.sqliteform;

/**
 * Created by Abidin on 20/02/2018.
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abidin.sqliteform.dbHelper.ProfilHelper;

import java.util.ArrayList;

public class ProfilAdapter extends RecyclerView.Adapter<ProfilAdapter.CustomViewHolder>{

    private LayoutInflater mInflater;
    private ArrayList<ProfilModel> profil;
    private Context context;
    private ProfilHelper profilHelper;


    public ProfilAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        profilHelper = new ProfilHelper(context);


    }

    // Create new views (invoked by the layout manager)
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                               int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                viewGroup.getContext());
        View v =
                inflater.inflate(R.layout.row_view, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String nama = profil.get(position).getName();
        final String nim = profil.get(position).getNim();
        final String keminatan = profil.get(position).getKeminatan();
        holder.editNama.setText(nama);
        holder.editNim.setText(nim);
        holder.editKeminatan.setText(keminatan);


        holder.btnupdate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                profil.get(position).setName(holder.editNama.getText().toString());
                profil.get(position).setNim(holder.editNim.getText().toString());
                profil.get(position).setKeminatan(holder.editKeminatan.getText().toString());

                profilHelper.open();
                profilHelper.update(profil.get(position));
                profilHelper.close();
                Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
        holder.btndelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteitem(profil.get(position).getId());
                profil.remove(position);
                notifyDataSetChanged();

            }
        });


    }

    private void deleteitem(int id) {

        profilHelper.open();
        profilHelper.delete(id);
        profilHelper.close();

        Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return profil.size();
    }

    public void addItem(ArrayList<ProfilModel> mData) {
        this.profil = mData;
        notifyDataSetChanged();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private EditText editNama, editNim, editKeminatan;
        private Button btnupdate, btndelete;
        private CardView cv;

        public CustomViewHolder(View itemView) {
            super(itemView);

            editNama = (EditText) itemView.findViewById(R.id.edit_nama);
            editNim = (EditText) itemView.findViewById(R.id.edit_nim);
            editKeminatan = (EditText) itemView.findViewById(R.id.edit_keminatan);
            btnupdate = (Button) itemView.findViewById(R.id.btn_update);
            btndelete = (Button) itemView.findViewById(R.id.btn_delete);
            cv = (CardView) itemView.findViewById(R.id.cv);


        }
    }

}

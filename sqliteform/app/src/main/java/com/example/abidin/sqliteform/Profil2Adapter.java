package com.example.abidin.sqliteform;

/**
 * Created by Abidin on 21/02/2018.
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

import com.example.abidin.sqliteform.dbHelper.Profil2Helper;

import java.util.ArrayList;

public class Profil2Adapter extends RecyclerView.Adapter<Profil2Adapter.CustomViewHolder>{

    private LayoutInflater mInflater;
    private ArrayList<Profil2Model> profil2;
    private Context context;
    private Profil2Helper profil2Helper;


    public Profil2Adapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        profil2Helper = new Profil2Helper(context);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                               int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                viewGroup.getContext());
        View v =
                inflater.inflate(R.layout.row_view2, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String namadosen = profil2.get(position).getNamedosen();
        final String nidk = profil2.get(position).getNidk();
        final String namaptn = profil2.get(position).getNamaptn();
        holder.editNamadosen.setText(namadosen);
        holder.editNidk.setText(nidk);
        holder.editNamaptn.setText(namaptn);


        holder.btnupdate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                profil2.get(position).setNamadosen(holder.editNamadosen.getText().toString());
                profil2.get(position).setNidk(holder.editNidk.getText().toString());
                profil2.get(position).setNamaptn(holder.editNamaptn.getText().toString());

                profil2Helper.open();
                profil2Helper.update(profil2.get(position));
                profil2Helper.close();
                Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
        holder.btndelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteitem(profil2.get(position).getId());
                profil2.remove(position);
                notifyDataSetChanged();

            }
        });


    }

    private void deleteitem(int id) {

        profil2Helper.open();
        profil2Helper.delete(id);
        profil2Helper.close();

        Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return profil2.size();
    }

    public void addItem(ArrayList<Profil2Model> mData) {
        this.profil2 = mData;
        notifyDataSetChanged();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private EditText editNamadosen, editNidk, editNamaptn;
        private Button btnupdate, btndelete;
        private CardView cv;

        public CustomViewHolder(View itemView) {
            super(itemView);

            editNamadosen = (EditText) itemView.findViewById(R.id.edit_namadosen);
            editNidk = (EditText) itemView.findViewById(R.id.edit_nidk);
            editNamaptn = (EditText) itemView.findViewById(R.id.edit_namaptn);
            btnupdate = (Button) itemView.findViewById(R.id.btn_update);
            btndelete = (Button) itemView.findViewById(R.id.btn_delete);
            cv = (CardView) itemView.findViewById(R.id.cv);


        }
    }

}

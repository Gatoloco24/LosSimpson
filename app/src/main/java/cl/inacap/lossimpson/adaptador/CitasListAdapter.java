package cl.inacap.lossimpson.adaptador;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;


import cl.inacap.lossimpson.R;
import cl.inacap.lossimpson.dto.Citas;

public class CitasListAdapter extends ArrayAdapter<Citas> {
    private List<Citas> citas;
    private Activity contexto;
    public CitasListAdapter(@NonNull Context context, int resource, @NonNull List<Citas> objects) {
        super(context, resource, objects);
        this.citas = objects;
        this.contexto = (Activity) context;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.contexto.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_citas,null,true);
        TextView nombreTxt = rowView.findViewById(R.id.nombre);
        TextView citaTxt = rowView.findViewById(R.id.cita);
        ImageView imageIM = rowView.findViewById(R.id.imagen);
        nombreTxt.setText(this.citas.get(position).getCharacter());
        citaTxt.setText(this.citas.get(position).getQuote());
        Picasso.get().load(this.citas.get(position).getImage())
                .resize(300,300)
                .centerCrop()
                .into(imageIM);
        return rowView;
    }
}
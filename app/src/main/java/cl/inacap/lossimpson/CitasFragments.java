package cl.inacap.lossimpson;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cl.inacap.lossimpson.adaptador.CitasListAdapter;
import cl.inacap.lossimpson.dto.Citas;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CitasFragments} factory method to
 * create an instance of this fragment.
 */
public class CitasFragments extends Fragment {

    private RequestQueue queue;
    private ListView listViewCitas;
    private List<Citas> citas = new ArrayList<>();
    private CitasListAdapter adaptador;
    public CitasFragments() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();
        queue = Volley.newRequestQueue(this.getActivity());
        //diferencia respecto a los activities
        this.listViewCitas = getView().findViewById(R.id.lista_citas);
        this.adaptador = new CitasListAdapter(this.getActivity(), R.layout.list_citas, this.citas);
        this.listViewCitas.setAdapter(this.adaptador);
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                "https://thesimpsonsquoteapi.glitch.me/quotes", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            citas.clear();
                            Citas[] citasObt = new Gson().fromJson(response.getString("quotes"),
                                    Citas[].class);
                            citas.addAll(Arrays.asList(citasObt));
                        }catch(Exception ex){
                            citas = null;
                        }finally {
                            adaptador.notifyDataSetChanged();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                citas = null;
                adaptador.notifyDataSetChanged();
            }
        });
        queue.add(jsonReq);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_citas_fragments, container, false);
    }
}
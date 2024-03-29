package es.iesnervion.mrequena.ejercfragment;



import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by mrequena on 23/11/16.
 */

public class HeadlinesFragment extends ListFragment {
    OnHeadlineSelectedListener mCallback;

    // la Activity contenedora debe implementar esta interfaz
    // para que el fragment pueda enviar mensajes
    public interface OnHeadlineSelectedListener{
        public void onArticleSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We need to use a different list item layout for devices older than Honeycomb
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;
        // Create an array adapter for the list view, using the Ipsum headlines array
        setListAdapter(new ArrayAdapter<String>(getActivity(),layout,Ipsum.Headlines)); //(,layout,)
    }

    @Override
    public void onStart() {
        super.onStart();

        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if(getFragmentManager().findFragmentById(R.id.article_fragment) != null){
            //si el layout tiene el articulo not null es xq esta en la tablet
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            //con este metodo se queda marcado el elemento de la lista que uso
            //CHOICE_MODE_SINGLE    sirve para poder devolver the set of checked items ids.
        // es mejor hacer un boolean que me diga al inicio de todo si estoy en tablet o en movil
        }
    }

    @Override
    public void onAttach(Activity activity) { //(Context context)
        super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try{
            mCallback = (OnHeadlineSelectedListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()
            + " debe implementar OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        //Notifica la actividad padre del item seleccionado
        mCallback.onArticleSelected(position);
        //Set the item as checked to be highlighted when in two-pane layout
        getListView().setItemChecked(position, true);
    }
}

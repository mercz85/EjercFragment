package es.iesnervion.mrequena.ejercfragment;

//import android.app.Fragment; esta no es!! es la de abajo
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mrequena on 23/11/16.
 */

public class ArticleFragment extends Fragment{
    final static String ARG_POSITION="position";
    int mCurrentPosition=-1; //-1
    TextView article;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       //Autogenerado: return super.onCreateView(inflater, container, savedInstanceState);

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.article_view, container, false);//(R.layout.article_view,...)
        //para poder coger elementos como botones, usaria v.findViewbyid()...
        View v = inflater.inflate(R.layout.article_view, container, false);
        article = (TextView) v.findViewById(R.id.article);
        return v;

    }

    public void onStart(){
        super.onStart();
        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if(args!=null){//el bundle se ha usado y tiene argumentos anteriores
            updateArticleView(args.getInt(ARG_POSITION));
        }else if(mCurrentPosition!=-1){
            //aqui entraria cuando cambio el fragment(¿¿se destruye cuando cambia de contenido y se crea nuevo??")
            //sin usar bundle y quiero saber cual tenia elegido previamente
         updateArticleView(mCurrentPosition);
        }
    }

    public void updateArticleView(int position) {
        //esto no haria falta usando lo de antes de v.findViewById(...)
     //   TextView article = (TextView) getView().findViewById(R.id.article); //ponia article_fragment
        // estp de arriba no funciona, he tenido que poner article como atributo de la clase y asignarlo en el onCreate
        this.article.setText(Ipsum.Articles[position]);
        mCurrentPosition = position;
    }

    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        //guardo la seleccion del articulo en un Bundle
        outState.putInt(ARG_POSITION,mCurrentPosition);
    }
}



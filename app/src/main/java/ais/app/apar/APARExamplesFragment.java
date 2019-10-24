package ais.app.apar;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.shockwave.pdfium.PdfDocument;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class APARExamplesFragment extends Fragment implements OnPageChangeListener, OnLoadCompleteListener,
        OnPageErrorListener {


    private static final String TAG = APARExamplesFragment.class.getSimpleName();


    private static final String ESOURCE_FILE = "electronicSources.pdf";
    private static final String SRESOURCE_FILE = "SResource.pdf";
    private static final String BOOKS_FILE = "books.pdf";
    private static final String JOP_FILE = "jouornalsOrperiodies.pdf";

    PDFView pdfView;

    private  Button btnESource;
    private  Button btnSResource;
    private  Button btnBooks;
    private  Button btnJOP;

    public APARExamplesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View examplesView = inflater.inflate(R.layout.fragment_aparexamples, container, false);
        pdfView = examplesView.findViewById(R.id.pdfView);
        pdfView.setBackgroundColor(Color.WHITE);
        displayFromAsset(ESOURCE_FILE);
        btnBooks = examplesView.findViewById(R.id.btnBooks);
        btnBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetButton();
                displayFromAsset(BOOKS_FILE);
                btnBooks.setEnabled(false);
                btnBooks.setTextColor(Color.BLUE);
            }
        });
        btnESource = examplesView.findViewById(R.id.btnESource);
        btnESource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetButton();
                displayFromAsset(ESOURCE_FILE);
                btnESource.setEnabled(false);
                btnESource.setTextColor(Color.BLUE);
            }
        });

        btnJOP = examplesView.findViewById(R.id.btnJOP);
        btnJOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetButton();
                displayFromAsset(JOP_FILE);
                btnJOP.setEnabled(false);
                btnJOP.setTextColor(Color.BLUE);
            }
        });
        btnSResource = examplesView.findViewById(R.id.btnSResource);
        btnSResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetButton();
                displayFromAsset(SRESOURCE_FILE);
                btnSResource.setEnabled(false);
                btnSResource.setTextColor(Color.BLUE);
            }
        });
        return examplesView;
    }

    private void resetButton(){
        btnESource.setTextColor(Color.BLACK);
        btnSResource.setTextColor(Color.BLACK);
        btnBooks.setTextColor(Color.BLACK);
        btnJOP.setTextColor(Color.BLACK);
        btnESource.setEnabled(true);
        btnSResource.setEnabled(true);
        btnBooks.setEnabled(true);
        btnJOP.setEnabled(true);
    }

    private void displayFromAsset(String assetFileName) {
        pdfView.fromAsset(assetFileName)
                .defaultPage(0)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(null)
                .spacing(10) // in dp
                .onPageError(this)
                .load();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        Log.e(TAG, "title = " + meta.getTitle());
        Log.e(TAG, "author = " + meta.getAuthor());
        Log.e(TAG, "subject = " + meta.getSubject());
        Log.e(TAG, "keywords = " + meta.getKeywords());
        Log.e(TAG, "creator = " + meta.getCreator());
        Log.e(TAG, "producer = " + meta.getProducer());
        Log.e(TAG, "creationDate = " + meta.getCreationDate());
        Log.e(TAG, "modDate = " + meta.getModDate());

        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    @Override
    public void onPageError(int page, Throwable t) {

    }
}

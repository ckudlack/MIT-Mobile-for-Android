package edu.mit.mitmobile2.libraries;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import edu.mit.mitmobile2.SimpleArrayAdapter;
import edu.mit.mitmobile2.TwoLineActionRow;

public class BookListAdapter extends SimpleArrayAdapter<BookItem> {
    private List<BookItem> items = null;
    
    public BookListAdapter(Context context, List<BookItem> items, int rowResourceId) {
        super(context, items, rowResourceId);
        this.items = items;
    }

    public void setLookupHandler(ListView listView, final String extras) {
        setOnItemClickListener(listView,
                new SimpleArrayAdapter.OnItemClickListener<BookItem>() {
                    @Override
                    public void onItemSelected(BookItem item) {
                        BookDetailActivity.launchActivity(getContext(), items, items.indexOf(item));
                    }
                }
        );
    }
    
    public void updateView(BookItem book, View view) {          
        TwoLineActionRow twoLineActionRow = (TwoLineActionRow) view;
        twoLineActionRow.setTitle(book.title);
//        
        twoLineActionRow.setSubtitle(book.id);
    }


}

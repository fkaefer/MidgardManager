package sexy.fkaefer.midgard;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import sexy.fkaefer.midgard.data.Chars;
import sexy.fkaefer.midgard.helper.ItemTouchHelperAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * An activity representing a list of Chars. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link CharsDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class CharsListActivity extends Fragment {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private View mFrag;
    Firebase charsRef;
    SimpleItemRecyclerViewAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        charsRef = new Firebase("https://midgard.firebaseio.com/chars");
        charsRef.keepSynced(true);
        mFrag = inflater.inflate(R.layout.activity_chars_list, container, false);
        FloatingActionButton fab = (FloatingActionButton) mFrag.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        View recyclerView = mFrag.findViewById(R.id.chars_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        charsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Chars.CharItem newChar = postSnapshot.getValue(Chars.CharItem.class);
                    newChar.setTempGw(newChar.getGw());
                    newChar.setId(postSnapshot.getKey());
                    if(!Chars.ITEM_MAP.containsKey(newChar.id)) {
                        Chars.addItem(newChar);
                    }
                }
                GwComperator comparator = new GwComperator();
                Collections.sort(Chars.ITEMS, comparator);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        if (getActivity().findViewById(R.id.chars_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        return mFrag;
    }

    private void  setupFABs(){

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        mAdapter = new SimpleItemRecyclerViewAdapter(Chars.ITEMS);
        recyclerView.setAdapter(mAdapter);
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> implements ItemTouchHelperAdapter {

        private final List<Chars.CharItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<Chars.CharItem> items) {
            mValues = items;
        }

        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(mValues, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(mValues, i, i - 1);
                }
            }
            notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onItemDismiss(int position) {
            mValues.remove(position);
            notifyItemRemoved(position);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chars_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(Integer.toString(mValues.get(position).tempGw));
            holder.mContentView.setText(mValues.get(position).Name);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(CharsDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        CharsDetailFragment fragment = new CharsDetailFragment();
                        fragment.setArguments(arguments);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.chars_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, CharsDetailActivity.class);
                        intent.putExtra(CharsDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                        context.startActivity(intent);
                    }
                }
            });
            holder.mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    switch(tab.getPosition()){
                        case 0:
                            holder.mItem.setTempGw(holder.mItem.getGw());
                            break;
                        case 1:
                            holder.mItem.setTempGw(holder.mItem.getGw()/2);
                            break;
                        case 2:
                            holder.mItem.setTempGw(-1);
                            break;
                        case 3:
                            holder.mItem.setTempGw(0);
                            break;
                        default:
                            break;
                    }
                    int oldPosition = mValues.indexOf(holder.mItem);
                    GwComperator comparator = new GwComperator();
                    Collections.sort(Chars.ITEMS, comparator);
                    mAdapter.notifyItemMoved(oldPosition, mValues.indexOf(holder.mItem));
                    //mAdapter.notifyItemMoved(oldPos, holder.getAdapterPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            /*holder.halfMove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.mItem.setTempGw(holder.mItem.getGw()/2);
                    GwComperator comparator = new GwComperator();
                    Collections.sort(Chars.ITEMS, comparator);
                    mAdapter.notifyDataSetChanged();
                }
            });*/
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public Chars.CharItem mItem;
            public final TabLayout mTabLayout;
            /*public final FloatingActionButton fullMove;
            public final FloatingActionButton halfMove;
            public final FloatingActionButton noMoveLeft;
            public final FloatingActionButton stall;*/

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.card_info_gw);
                mContentView = (TextView) view.findViewById(R.id.card_info_name);
                mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
                if (mTabLayout == null)
                    return;
                mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.ic_hourglass_full_white_24dp));
                mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.ic_half));
                mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.ic_hourglass_empty_white_24dp));
                mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.ic_low_priority_white_24dp));
                /*fullMove = (FloatingActionButton) view.findViewById(R.id.noMovement);
                halfMove = (FloatingActionButton) view.findViewById(R.id.halfMovement);
                noMoveLeft = (FloatingActionButton) view.findViewById(R.id.fullMovement);
                stall = (FloatingActionButton) view.findViewById(R.id.stall);*/
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }

    public class GwComperator implements Comparator<Chars.CharItem> {
        @Override
        public int compare(Chars.CharItem lhs, Chars.CharItem rhs) {
            return lhs.getTempGw()> rhs.getTempGw() ? -1 : (lhs.getTempGw()< rhs.getTempGw() ? 1 : 0);
        }
    }
}

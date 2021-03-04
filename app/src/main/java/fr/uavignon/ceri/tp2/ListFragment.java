package fr.uavignon.ceri.tp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import fr.uavignon.ceri.tp2.data.Book;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ListFragment extends Fragment {

    RecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ListViewModel viewModel;

    RecyclerAdapter adapter2;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ListViewModel.class);
        observerSetup();
        recyclerSetup();


    }

    private void recyclerSetup() {
        RecyclerView recyclerView;
        adapter = new RecyclerAdapter();
        recyclerView = getView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void observerSetup() {
        viewModel.getAllBooks().observe(getViewLifecycleOwner(),
                Books -> adapter.setBookList(Books));
    }
}
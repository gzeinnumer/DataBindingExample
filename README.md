# DataBindingExample

- [Library Data Binding](https://developer.android.com/topic/libraries/data-binding?hl=id)

**Important** enable `DataBinding` on your project, setup in `gradle`.

```gradle
android {

    ...
    
    //Android Studio Version Until 4
    dataBinding {
        enabled = true
    }
    
    //Android Studio Version 4 -> gradle version 6.1.1 -> android gradle plugin version 4.0.0
    buildFeatures{
        dataBinding = true
    }

}
```

**Add Dependencies** to enable ViewModel.
```gradle
dependencies {
    
    ...
    
    //mvvm
    implementation 'android.arch.lifecycle:viewmodel:1.1.1'
    implementation 'android.arch.lifecycle:livedata:1.1.1'
    implementation 'android.arch.lifecycle:extensions:1.1.1'
}
```

**ViewModel Example**.
```java
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    public MyViewModel() {
    }
}
```

#
#### DataBinding On Activity

> activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout>

    <data>

        <variable
            name="viewModel"
            type="com.gzeinnumer.databindingexample.MyViewModel" />
    </data>
    
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    
        <TextView
            android:text="Hallo Zein"
            android:id="@+id/my_text_view"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"/>
    
    </LinearLayout>
</layout>
```

> MainActivity.java

```java
public class MainActivity extends AppCompatActivity implements AdapterRVMultiType.MyOnClick {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(new MyViewModel());
        
        binding.myTextView.setText("Hallo GZeinNumer");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
```
[FullCode](https://github.com/gzeinnumer/DataBindingExample/blob/master/app/src/main/java/com/gzeinnumer/databindingexample/MainActivity.java)

#
#### DataBinding On Fragment

> fragment_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout>

    <data>

        <variable
            name="viewModel"
            type="com.gzeinnumer.databindingexample.MyViewModel" />
    </data>
    
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    
        <TextView
            android:text="Hallo Zein"
            android:id="@+id/my_text_view"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"/>
    
    </LinearLayout>
</layout>
```

> MainFragment.java

```java
public class MainFragment extends Fragment {

    private FragmentMainBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_main);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
```
[FullCode](https://github.com/gzeinnumer/DataBindingExample/blob/master/app/src/main/java/com/gzeinnumer/databindingexample/MainFragment.java)

#
#### DataBinding On AdapterRecyclerView (Single Type)

> item_list.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout>

    <data>

        <variable
            name="viewModel"
            type="com.gzeinnumer.databindingexample.MyViewModel" />
    </data>
    
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    
        <TextView
            android:text="Hallo Zein"
            android:id="@+id/my_text_view"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"/>
    
    </LinearLayout>
</layout>
```

> AdapterRV.java

```java
public class AdapterRV extends RecyclerView.Adapter<AdapterRV.MyHolder> {
    
    ...
    
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(ItemListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        ItemAdapterRvBinding binding;

        public MyHolder(ItemAdapterRvBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bindData(String data){
            binding.myTextView.setText(data);
        }
    }
}
```
[FullCode](https://github.com/gzeinnumer/DataBindingExample/blob/master/app/src/main/java/com/gzeinnumer/databindingexample/AdapterRV.java)

#
#### DataBinding On AdapterRecyclerView (Multi Type)

> item_list.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout>

    <data>

        <variable
            name="viewModel"
            type="com.gzeinnumer.databindingexample.MyViewModel" />
    </data>
    
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    
        <TextView
            android:text="Hallo Zein"
            android:id="@+id/my_text_view"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"/>
    
    </LinearLayout>
</layout>
```

> AdapterRV.java

```java
public class AdapterRV extends RecyclerView.Adapter<AdapterRV.MyHolder> {
    
    ...
    
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (holder.getItemViewType() == TYPE_NORMAL) {
            return new MyHolder(ItemListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        } else {
            throw new IllegalStateException("Unexpected value: " + holder.getItemViewType());
        }
        
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        ItemAdapterRvBinding binding;

        public MyHolder(ItemAdapterRvBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bindData(String data){
            binding.myTextView.setText(data);
        }
    }
    
    private static int TYPE_NORMAL = 1;

    @Override
    public int getItemViewType(int position) {
        if (position!=-1){
            return TYPE_NORMAL;
        } else {
            return 0;
        }
    }
}
```
[FullCode](https://github.com/gzeinnumer/DataBindingExample/blob/master/app/src/main/java/com/gzeinnumer/databindingexample/AdapterRVMultiType.java)

#
#### DataBinding On DialogFragment

> fragment_main_dialog.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout>

    <data>

        <variable
            name="viewModel"
            type="com.gzeinnumer.databindingexample.MyViewModel" />
    </data>
    
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    
        <TextView
            android:text="Hallo Zein"
            android:id="@+id/my_text_view"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"/>
    
    </LinearLayout>
</layout>
```

> MainDialog.java

```java
public class MainDialog extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_main_dialog);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        binding.myTextView.setText("Hallo GZeinNumer");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
```
[FullCode](https://github.com/gzeinnumer/DataBindingExample/blob/master/app/src/main/java/com/gzeinnumer/databindingexample/MainDialog.java)

---

```
Copyright 2020 M. Fadli Zein
```
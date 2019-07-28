package com.smartcard.iotaspirantsvendor;


import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.smartcard.iotaspirantsvendor.data.model.AbstractResponse;
import com.smartcard.iotaspirantsvendor.data.model.Product;
import com.smartcard.iotaspirantsvendor.databinding.FragmentAddProductBinding;
import com.smartcard.iotaspirantsvendor.provider.VolleyRequest;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddProductFragment extends Fragment implements View.OnClickListener {

    Product product = new Product();
    private EditText Code;
    private EditText Color;
    private EditText Description;
    private EditText Location;
    private EditText Name;
    private EditText Offer;
    private EditText Price;
    private RequestQueue mRequestQueue;

    public AddProductFragment() {
        // Required empty public constructor
    }

    public static AddProductFragment newInstance() {
        AddProductFragment addProductFragment = new AddProductFragment();
        Bundle bundle = new Bundle();
        return addProductFragment;
    }

/*    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Product product = new Product();
        FragmentAddProductBinding binding = FragmentAddProductBinding.inflate(getActivity().getLayoutInflater());
        binding.setAddProductFragment(this);
        binding.setProduct(product);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //here data must be an instance of the class MarsDataProvider

        /*FragmentAddProductBinding binding = FragmentAddProductBinding.inflate(getActivity().getLayoutInflater());
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        //binding.setMarsdata(data);
        return view;*/
        View rootView =  inflater.inflate(R.layout.fragment_add_product, container, false);
        mRequestQueue = Volley.newRequestQueue(getActivity());
        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(this);
        Code = rootView.findViewById(R.id.code);
        Color = rootView.findViewById(R.id.color);
        Description = rootView.findViewById(R.id.description);
        Location = rootView.findViewById(R.id.location);
        Name = rootView.findViewById(R.id.name);
        Offer = rootView.findViewById(R.id.offer);
        Price = rootView.findViewById(R.id.price);
        return rootView;

    }

    public void onCategoryClick(View view)
    {
        Toast.makeText(getActivity(), "MAZ", Toast.LENGTH_LONG).show();
        int a =1 ;
    }

    @Override
    public void onClick(View view) {
        product.setCode(Code.getText().toString());
        product.setColor(Color.getText().toString());
        product.setDescription(Description.getText().toString());
        product.setLocation(Location.getText().toString());
        product.setName(Name.getText().toString());
        product.setOffer(Offer.getText().toString());
        product.setPrice(Price.getText().toString());
        VolleyRequest.save(new Response.Listener<AbstractResponse>() {
            @Override
            public void onResponse(AbstractResponse response) {
                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
            }
        }, product, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

            }
        }).enqueue(mRequestQueue);;
        Toast.makeText(getActivity(), "MAZ", Toast.LENGTH_LONG).show();
    }
}

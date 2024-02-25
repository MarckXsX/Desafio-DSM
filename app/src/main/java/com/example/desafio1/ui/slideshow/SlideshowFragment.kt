package com.example.desafio1.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.desafio1.R
import com.example.desafio1.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Colocar codigo aqui
        val MiNombre: EditText = root.findViewById(R.id.TxtNombre)
        val MiSalario: EditText = root.findViewById(R.id.TxtSalario)
        val Calcular: Button = root.findViewById(R.id.BtnCalcular)
        val Limpiar: Button = root.findViewById(R.id.BtnLimpiar)
        val Resultado: TextView = root.findViewById(R.id.lblResultado)

        Calcular.setOnClickListener {
            val nombre = MiNombre.text.toString().trim()
            val salario = MiSalario.text.toString().trim()

            if(nombre.isNotEmpty() && salario.isNotEmpty()){
                val Salario = salario.toFloatOrNull()

                if(Salario != null){
                    val SalarioBase = salario.toFloat()
                    if(SalarioBase > 0 ){

                        val iss = SalarioBase*0.03f
                        val afp = SalarioBase*0.04f
                        val renta = SalarioBase*0.05f

                        val SalarioNeto = SalarioBase - iss - afp - renta

                        Resultado.text = "$nombre, su salrio neto es de: $$SalarioNeto"
                    }else{
                        Resultado.text = "Ingrese valores Positivos."
                    }
                }else{
                    Resultado.text = "Ingrese datos validos."
                }
            }else{
                Resultado.text = "Ingresar tanto el nombre como su salario."
            }
        }

        Limpiar.setOnClickListener {
            MiNombre.text.clear()
            MiSalario.text.clear()
            Resultado.text = ""
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
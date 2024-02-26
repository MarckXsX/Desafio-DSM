package com.example.desafio1.ui.gallery

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
import com.example.desafio1.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val Minumero1: EditText =  root.findViewById(R.id.TxtNumero1)
        val Minumero2: EditText = root.findViewById(R.id.TxtNumero2)
        val Multiplicar: Button = root.findViewById(R.id.BtnMultiplicar)
        val Dividir: Button = root.findViewById(R.id.BtnDividir)
        val Sumar: Button = root.findViewById(R.id.BtnSuma)
        val Resta: Button = root.findViewById(R.id.BtnResta)
        val Calcular:Button = root.findViewById(R.id.BtnCalcular)
        val Resultado: TextView = root.findViewById(R.id.lblResultado)
        var result: Double = 0.0
        var operador = ""

        Multiplicar.setOnClickListener{ operador = "*"}
        Dividir.setOnClickListener{operador = "/"}
        Sumar.setOnClickListener{operador = "+"}
        Resta.setOnClickListener{ operador = "-"}

        Calcular.setOnClickListener {
            val numero1 = Minumero1.text.toString().trim()
            val numero2 = Minumero2.text.toString().trim()

            if(numero1.isNotEmpty() && numero2.isNotEmpty()){

                val num1 = numero1.toDoubleOrNull()
                val num2 = numero2.toDoubleOrNull()

                if(num1 != null && num2 != null){
                    when (operador) {
                        "*" -> {
                            result = num1 * num2
                            Resultado.text = "Resultado: $result"
                        }
                        "/" -> {
                            if (num2 == 0.0) {
                                Resultado.text = "No se puede dividir entre cero"
                            } else {
                                result = num1 / num2
                                Resultado.text = "Resultado: $result"
                            }
                        }
                        "+" -> {
                            result = num1 + num2
                            Resultado.text = "Resultado: $result"
                        }
                        "-" -> {
                            result = num1 - num2
                            Resultado.text = "Resultado: $result"
                        }
                        else -> {
                            Resultado.text = "Operador no ingresado"
                        }
                    }
                }else{
                    Resultado.text ="Ingrese valores numericos correctos."
                }


            }else{
                Resultado.text = "Por favor, no dejar espacios vacios."
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.desafio1.ui.home

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.desafio1.R
import com.example.desafio1.databinding.FragmentHomeBinding

private val notas = mutableListOf<Float>()



class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val MiNombre: EditText = root.findViewById(R.id.TxtNombre)
        val MisNotas: EditText = root.findViewById(R.id.TxtNotas)
        val Agregar: Button = root.findViewById(R.id.BtnAgregar)
        val Enviar: Button = root.findViewById(R.id.BtnEnviar)
        val Result: TextView = root.findViewById(R.id.lblResultado)
        val Result2: TextView = root.findViewById(R.id.lblResultado2)
        val Limpiar: Button = root.findViewById(R.id.BtnLimpiar)

        Agregar.setOnClickListener {
            val notaString = MisNotas.text.toString().trim()

            if (notaString.isNotEmpty()) {
                val nota = notaString.toFloatOrNull()

                if (nota != null) {

                    if (notas.size < 5) {
                        if(nota >= 0 && nota <=10) {
                            notas.add(nota)
                            MisNotas.text.clear()
                            Result.text = "Notas ingresadas: ${notas.joinToString()}"
                        }else{
                            Result.text = "Por favor, ingresar notas mayor a 0 y menor que 10."
                        }
                    } else {
                        Result.text = "Ya has ingresado las 5 notas"
                    }
                } else {
                    Result.text = "Por favor, introduce una nota válida"
                }
            } else {
                Result.text = "Por favor, introduce una nota"
            }
        }

        Enviar.setOnClickListener {
            val nombre: String = MiNombre.text.toString().trim()
            if (notas.size == 5 && nombre.isNotEmpty()) {
                val promedio = notas.average()
                if(promedio >= 6){
                    Result2.text = "Felicidades $nombre estas Aprobado."
                    Result.text = "El promedio de las notas es: $promedio"
                }else{
                    Result.text = "Felicidades $nombre estas Reprobado"
                    Result.text = "El promedio de las notas es: $promedio"
                }

            } else {
                Result.text = "Por favor, introduce las 5 notas antes de calcular el promedio y su nombre."
            }
        }

        Limpiar.setOnClickListener {
            MisNotas.text.clear()
            MiNombre.text.clear()
            notas.clear()
            Result.text = "Notas Borradas"
            Result2.text = ""
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
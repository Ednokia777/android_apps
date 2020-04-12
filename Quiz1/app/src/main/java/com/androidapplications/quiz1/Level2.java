package com.androidapplications.quiz1;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level2 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft;//переменная для левой картинки.
    public int numRight;//переменная для правой картинки
    public int numDownLeft;//переменная для нижней левой картинки
    public int numDownRight;//переменная для верхней левой картинки
    Array array = new Array();//создали объект из из класса Array
    Random random = new Random(); //Для генерации случайных чисел
    public int count = 0;//счетчик правильных ответов

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //создаем переменную text_levels
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level2);

        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        //кругляем углы для верхней левой картинки
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        //кругляем углы для верхней правой картинки
        img_right.setClipToOutline(true);

        final ImageView img_down_left = (ImageView)findViewById(R.id.img_down_left);
        //кругляем углы для нижней левой картинки
        img_down_left.setClipToOutline(true);

        final ImageView img_down_right = (ImageView)findViewById(R.id.img_down_right);
        //кругляем углы для нижней правой картинки
        img_down_right.setClipToOutline(true);

        //путь к левой TextView
        final TextView text_left = findViewById(R.id.text_left);
        //путь к правой TextView
        final TextView text_right = findViewById(R.id.text_right);
        //путь к нижней левой TextView
        final TextView text_down_left = findViewById(R.id.text_down_left);
        //путь к нижней правой TextView
        final TextView text_down_right = findViewById(R.id.text_down_right);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Window w = getWindow();
        //w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //Вызов диалогового экрана в начале игры
        dialog = new Dialog(this); //создаем новое диалоговое окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//скрываем заголовог
        dialog.setContentView(R.layout.previewdialog);//путь к макету диалога
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон диалогового окна.
        dialog.setCancelable(false);//окно неьзя закрыть системной кнопкой назад
        dialog.show();//показать диалоговое окно.

        //Устанавливаем картинку в диалоговое окно - начало
        ImageView previewimg =(ImageView)dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.fedor_ioanovish_dead);
        //Устанавливаем картинку в диалоговое окно - конец

        //устанавливаем описание задания - начало
        TextView textdescription = (TextView)dialog.findViewById(R.id.textdescription);
        textdescription.setText(R.string.leveltwo);
        //устанавливаем описание задания - начало



        //кнопка, которая закрывает диалоговое окно - начало
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабатываем нажатие кнопки - начало
                try{
                    //вернуться назад к выбору уровня - начало
                    Intent intent = new Intent(Level2.this, GameLevels.class);//создали намерение для перехода
                    startActivity(intent);//старт намерения
                    finish();//закрыть этот класс
                    //вернуться назад к выбору уровня - конец
                }catch (Exception e){
                    //пусто
                }
                dialog.dismiss();//закрываем диалоговое окно
                //обрабатываем нажатие кнопки - конец
            }
        });
        //кнопка, которая закрывает диалоговое окно - конец

        //Кнопка "Продолжить" начало.
        Button btncontinue = (Button)dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();//закрываем диалоговое окно
            }
        });
        //Кнопка "Продолжить" конец.

        dialog.show();//показать диалоговое окно

        //________________________________________________________________________________________

        //Вызов диалогового экрана в конце игры
        dialogEnd = new Dialog(this); //создаем новое диалоговое окно
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);//скрываем заголовог
        dialogEnd.setContentView(R.layout.dialogend);//пуьб к макету диалога
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон диалогового окна.
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);//окно неьзя закрыть системной кнопкой назад


        //Интересный факт - начало
        TextView textdescriptionEnd = (TextView)dialogEnd.findViewById(R.id.textdescriptionEnd);
        textdescriptionEnd.setText(R.string.leveltwoEnd);

        //Интересный факт - конец

        //dialogEnd.show();//показать диалоговое окно.

        //кнопка, которая закрывает диалоговое окно - начало
        TextView btnclose2 = (TextView)dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабатываем нажатие кнопки - начало
                try{
                    //вернуться назад к выбору уровня - начало
                    Intent intent = new Intent(Level2.this, GameLevels.class);//создали намерение для перехода
                    startActivity(intent);//старт намерения
                    finish();//закрыть этот класс
                    //вернуться назад к выбору уровня - конец
                }catch (Exception e){
                    //пусто
                }
                dialogEnd.dismiss();//закрываем диалоговое окно
                //обрабатываем нажатие кнопки - конец
            }
        });
        //кнопка, которая закрывает диалоговое окно - конец

        //Кнопка "Продолжить" начало.
        Button btncontinue2 = (Button)dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level2.this, Level3.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    //тут пусто
                }
                dialogEnd.dismiss();//закрываем диалоговое окно
            }
        });
        //Кнопка "Продолжить" конец.
        //________________________________________________________________________________________

        //кнопка "назад" - начало.
        Button btn_back = (Button)findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабатываем нажатие кнопки назад - начало
                try{
                    //вернуться к началу выбора уровня - начало.
                    Intent intent = new Intent(Level2.this, GameLevels.class);//создали намерение для перехода
                    startActivity(intent);//старт намерения
                    finish();
                    //вернуться к началу выбора уровня - конец.
                }catch (Exception e){
                    //пусто
                }
                //обрабатываем нажатие кнопки назад - конец

            }
        });
        //кнопка "назад" - конец.


        //массив для прогресса игры - начало
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,

        };
        //массив для прогресса игры - конец

        //подключаем анимацию - начало
        final Animation a = AnimationUtils.loadAnimation(Level2.this, R.anim.alpha);
        //подключаем анимацию - конец


        //Цикл с предусловием, проверяющий равенство событий - начало
        numLeft = random.nextInt(19);//генерируем случайное число от 0 до 16.
        img_left.setImageResource(array.images2[numLeft]);//достаем из массива картинку
        text_left.setText(array.texts2[numLeft]);//достаем из массива текст

        numRight = random.nextInt(19);//генерируем случайное число от 0 до 16.
        while(numLeft == numRight){
            numRight = random.nextInt(19);
            if(numLeft == numRight){
                continue;
            }
        }
        img_right.setImageResource(array.images2[numRight]);//достаем из массива картинку
        text_right.setText(array.texts2[numRight]);//достаем из массива текст

        numDownLeft = random.nextInt(19);//генерируем случайное число от 0 до 16.
        while(numLeft == numDownLeft || numRight == numDownLeft){
            numDownLeft = random.nextInt(19);
            if(numLeft == numDownLeft || numRight == numDownLeft){
                continue;
            }
        }
        img_down_left.setImageResource(array.images2[numDownLeft]);//достаем из массива картинку
        text_down_left.setText(array.texts2[numDownLeft]);//достаем из массива текст

        numDownRight = random.nextInt(19);//генерируем случайное число от 0 до 16.
        while(numLeft == numDownRight || numRight == numDownRight || numDownLeft == numDownRight){
            numDownRight = random.nextInt(19);
            if(numLeft == numDownRight || numRight == numDownRight || numDownLeft == numDownRight){
                continue;
            }
        }
        img_down_right.setImageResource(array.images2[numDownRight]);//достаем из массива картинку
        text_down_right.setText(array.texts2[numDownRight]);//достаем из массива текст
        //Цикл с предусловием, проверяющий равенство чисел - конец

        //обрабатываем нажатие на левую картинку - начало
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //условие касания  левой картинки - начало
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    //Если каснулся картинки - начало
                    img_right.setEnabled(false); //блокируем правую картинку
                    img_down_left.setEnabled(false); //блокируем нижнюю левую картинку
                    img_down_right.setEnabled(false); //блокируем нижнюю правую картинку
                    if (numLeft > numRight && numLeft > numDownLeft && numLeft > numDownRight){
                        img_left.setImageResource(R.drawable.img_true);
                    }else{
                        img_left.setImageResource(R.drawable.img_false);
                    }
                    //если каснулся картинки - конец
                }else if (event.getAction()== MotionEvent.ACTION_UP){
                    //Если отпустил палец - начало
                    if(numLeft > numRight && numLeft > numDownLeft && numLeft > numDownRight){
                        //если левая картинка больше
                        if (count < 20){
                            count++;
                        }
                        //закрашиваем прогресс серым цветом - начало
                        for(int i = 0; i < 20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //закрашиваем прогресс серым цветом - конец.

                        //Определяем правильные ответы и закрашиваем зеленным цветом - начало
                        for(int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленным цветом - конец


                    }else{
                        if(count > 0){
                            if(count == 1){
                                count = 0;
                            }else{
                                count = count - 2;
                            }
                        }
                        //закрашиваем прогресс серым цветом - начало
                        for(int i = 0; i < 19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //закрашиваем прогресс серым цветом - конец.

                        //Определяем правильные ответы и закрашиваем зеленным цветом - начало
                        for(int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленным цветом - конец
                        //если левая картинка меньше
                    }

                    //Если отпустил палец - конец
                    if(count == 20){
                        //ВЫХОД ИЗ УРОВНЯ
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);
                        if(level > 2){
                            //пустр
                        }else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 3);
                            editor.commit();
                        }
                        dialogEnd.show();
                    }else{
                        //Цикл с предусловием, проверяющий равенство событий - начало
                        numLeft = random.nextInt(19);//генерируем случайное число от 0 до 16.
                        img_left.setImageResource(array.images2[numLeft]);//достаем из массива картинку
                        text_left.setText(array.texts2[numLeft]);//достаем из массива текст

                        numRight = random.nextInt(19);//генерируем случайное число от 0 до 16.
                        while(numLeft == numRight){
                            numRight = random.nextInt(19);
                            if(numLeft == numRight){
                                continue;
                            }
                        }
                        img_right.setImageResource(array.images2[numRight]);//достаем из массива картинку
                        text_right.setText(array.texts2[numRight]);//достаем из массива текст

                        numDownLeft = random.nextInt(19);//генерируем случайное число от 0 до 19.
                        while(numLeft == numDownLeft || numRight == numDownLeft){
                            numDownLeft = random.nextInt(19);
                            if(numLeft == numDownLeft || numRight == numDownLeft){
                                continue;
                            }
                        }
                        img_down_left.setImageResource(array.images2[numDownLeft]);//достаем из массива картинку
                        text_down_left.setText(array.texts2[numDownLeft]);//достаем из массива текст

                        numDownRight = random.nextInt(19);//генерируем случайное число от 0 до 19.
                        while(numLeft == numDownRight || numRight == numDownRight || numDownLeft == numDownRight){
                            numDownRight = random.nextInt(19);
                            if(numLeft == numDownRight || numRight == numDownRight || numDownLeft == numDownRight){
                                continue;
                            }
                        }
                        img_down_right.setImageResource(array.images2[numDownRight]);//достаем из массива картинку
                        text_down_right.setText(array.texts2[numDownRight]);//достаем из массива текст
                        //Цикл с предусловием, проверяющий равенство чисел - конец

                        img_right.setEnabled(true);//включаем обратно правую картинку
                        img_down_left.setEnabled(true);//включаем обратно нижнюю левую картинку
                        img_down_right.setEnabled(true);//включаем обратно нижнюю правую картинку
                    }
                }
                //условие касания кратинки - конец
                return true;
            }
        });
        //обрабатываем нажатие на левую картинку - конец


        //обрабатываем нажатие на правую картинку - начало
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //условие касания картинки - начало
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    //Если каснулся картинки - начало
                    img_left.setEnabled(false); //блокируем левую картинку
                    img_down_left.setEnabled(false); //блокируем нижнюю левую картинку
                    img_down_right.setEnabled(false); //блокируем нижнюю правую картинку
                    if (numRight > numLeft && numRight > numDownLeft && numRight > numDownRight){
                        img_right.setImageResource(R.drawable.img_true);
                    }else{
                        img_right.setImageResource(R.drawable.img_false);
                    }
                    //если каснулся картинки - конец
                }else if (event.getAction()== MotionEvent.ACTION_UP){
                    //Если отпустил палец - начало
                    if(numRight > numLeft && numRight > numDownLeft && numRight > numDownRight){
                        //если правая картинка больше
                        if (count < 20){
                            count++;
                        }
                        //закрашиваем прогресс серым цветом - начало
                        for(int i = 0; i < 20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //закрашиваем прогресс серым цветом - конец.

                        //Определяем правильные ответы и закрашиваем зеленным цветом - начало
                        for(int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленным цветом - конец


                    }else{
                        //если правая картинка меньше
                        if(count > 0){
                            if(count == 1){
                                count = 0;
                            }else{
                                count = count - 2;
                            }
                        }
                        //закрашиваем прогресс серым цветом - начало
                        for(int i = 0; i < 19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //закрашиваем прогресс серым цветом - конец.

                        //Определяем правильные ответы и закрашиваем зеленным цветом - начало
                        for(int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленным цветом - конец
                    }

                    //Если отпустил палец - конец
                    if(count == 20){
                        //ВЫХОД ИЗ УРОВНЯ
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);
                        if(level > 2){
                            //пустр
                        }else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 3);
                            editor.commit();
                        }
                        dialogEnd.show();
                    }else{
                        //Цикл с предусловием, проверяющий равенство событий - начало
                        numLeft = random.nextInt(19);//генерируем случайное число от 0 до 16.
                        img_left.setImageResource(array.images2[numLeft]);//достаем из массива картинку
                        text_left.setText(array.texts2[numLeft]);//достаем из массива текст

                        numRight = random.nextInt(19);//генерируем случайное число от 0 до 16.
                        while(numLeft == numRight){
                            numRight = random.nextInt(19);
                            if(numLeft == numRight){
                                continue;
                            }
                        }
                        img_right.setImageResource(array.images2[numRight]);//достаем из массива картинку
                        text_right.setText(array.texts2[numRight]);//достаем из массива текст

                        numDownLeft = random.nextInt(19);//генерируем случайное число от 0 до 16.
                        while(numLeft == numDownLeft || numRight == numDownLeft){
                            numDownLeft = random.nextInt(19);
                            if(numLeft == numDownLeft || numRight == numDownLeft){
                                continue;
                            }
                        }
                        img_down_left.setImageResource(array.images2[numDownLeft]);//достаем из массива картинку
                        text_down_left.setText(array.texts2[numDownLeft]);//достаем из массива текст

                        numDownRight = random.nextInt(19);//генерируем случайное число от 0 до 16.
                        while(numLeft == numDownRight || numRight == numDownRight || numDownLeft == numDownRight){
                            numDownRight = random.nextInt(19);
                            if(numLeft == numDownRight || numRight == numDownRight || numDownLeft == numDownRight){
                                continue;
                            }
                        }
                        img_down_right.setImageResource(array.images2[numDownRight]);//достаем из массива картинку
                        text_down_right.setText(array.texts2[numDownRight]);//достаем из массива текст
                        //Цикл с предусловием, проверяющий равенство чисел - конец

                        img_left.setEnabled(true);//включаем обратно левую картинку
                        img_down_left.setEnabled(true);//включаем обратно нижнюю левую картинку
                        img_down_right.setEnabled(true);//включаем обратно нижнюю правую картинку
                    }
                }
                //условие касания кратинки - конец
                return true;
            }
        });
        //обрабатываем нажатие на правую картинку - конец


        //обрабатываем нажатие на нижнюю левую картинку - начало
        img_down_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //условие касания картинки - начало
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    //Если каснулся картинки - начало
                    img_left.setEnabled(false); //блокируем левую картинку
                    img_right.setEnabled(false); //блокируем нижнюю левую картинку
                    img_down_right.setEnabled(false); //блокируем нижнюю правую картинку
                    if (numDownLeft  > numLeft && numDownLeft > numRight && numDownLeft > numDownRight){
                        img_down_left.setImageResource(R.drawable.img_true);
                    }else{
                        img_down_left.setImageResource(R.drawable.img_false);
                    }
                    //если каснулся картинки - конец
                }else if (event.getAction()== MotionEvent.ACTION_UP){
                    //Если отпустил палец - начало
                    if(numDownLeft  > numLeft && numDownLeft > numRight && numDownLeft > numDownRight){
                        //если нижняя левая картинка больше
                        if (count < 20){
                            count++;
                        }
                        //закрашиваем прогресс серым цветом - начало
                        for(int i = 0; i < 20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //закрашиваем прогресс серым цветом - конец.

                        //Определяем правильные ответы и закрашиваем зеленным цветом - начало
                        for(int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленным цветом - конец


                    }else{
                        //если нижняя левая картинка меньше
                        if(count > 0){
                            if(count == 1){
                                count = 0;
                            }else{
                                count = count - 2;
                            }
                        }
                        //закрашиваем прогресс серым цветом - начало
                        for(int i = 0; i < 19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //закрашиваем прогресс серым цветом - конец.

                        //Определяем правильные ответы и закрашиваем зеленным цветом - начало
                        for(int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленным цветом - конец
                    }

                    //Если отпустил палец - конец
                    if(count == 20){
                        //ВЫХОД ИЗ УРОВНЯ
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);
                        if(level > 2){
                            //пустр
                        }else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 3);
                            editor.commit();
                        }
                        dialogEnd.show();
                    }else{
                        //Цикл с предусловием, проверяющий равенство событий - начало
                        numLeft = random.nextInt(19);//генерируем случайное число от 0 до 16.
                        img_left.setImageResource(array.images2[numLeft]);//достаем из массива картинку
                        text_left.setText(array.texts2[numLeft]);//достаем из массива текст

                        numRight = random.nextInt(19);//генерируем случайное число от 0 до 16.
                        while(numLeft == numRight){
                            numRight = random.nextInt(19);
                            if(numLeft == numRight){
                                continue;
                            }
                        }
                        img_right.setImageResource(array.images2[numRight]);//достаем из массива картинку
                        text_right.setText(array.texts2[numRight]);//достаем из массива текст

                        numDownLeft = random.nextInt(19);//генерируем случайное число от 0 до 16.
                        while(numLeft == numDownLeft || numRight == numDownLeft){
                            numDownLeft = random.nextInt(19);
                            if(numLeft == numDownLeft || numRight == numDownLeft){
                                continue;
                            }
                        }
                        img_down_left.setImageResource(array.images2[numDownLeft]);//достаем из массива картинку
                        text_down_left.setText(array.texts2[numDownLeft]);//достаем из массива текст

                        numDownRight = random.nextInt(19);//генерируем случайное число от 0 до 16.
                        while(numLeft == numDownRight || numRight == numDownRight || numDownLeft == numDownRight){
                            numDownRight = random.nextInt(19);
                            if(numLeft == numDownRight || numRight == numDownRight || numDownLeft == numDownRight){
                                continue;
                            }
                        }
                        img_down_right.setImageResource(array.images2[numDownRight]);//достаем из массива картинку
                        text_down_right.setText(array.texts2[numDownRight]);//достаем из массива текст
                        //Цикл с предусловием, проверяющий равенство чисел - конец

                        img_left.setEnabled(true);//включаем обратно левую картинку
                        img_right.setEnabled(true);//включаем обратно правую картинку
                        img_down_right.setEnabled(true);//включаем обратно нижнюю правую картинку
                    }
                }
                //условие касания кратинки - конец
                return true;
            }
        });
        //обрабатываем нажатие на нижнюю левую картинку - конец

        //обрабатываем нажатие на нижнюю правую картинку - начало
        img_down_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //условие касания картинки - начало
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    //Если каснулся картинки - начало
                    img_left.setEnabled(false); //блокируем левую картинку
                    img_right.setEnabled(false); //блокируем правую картинку
                    img_down_left.setEnabled(false); //блокируем нижнюю левую картинку
                    if (numDownRight  > numLeft && numDownRight > numRight && numDownRight > numDownLeft){
                        img_down_right.setImageResource(R.drawable.img_true);
                    }else{
                        img_down_right.setImageResource(R.drawable.img_false);
                    }
                    //если каснулся картинки - конец
                }else if (event.getAction()== MotionEvent.ACTION_UP){
                    //Если отпустил палец - начало
                    if(numDownRight  > numLeft && numDownRight > numRight && numDownRight > numDownLeft){
                        //если нижняя правая картинка больше
                        if (count < 20){
                            count++;
                        }
                        //закрашиваем прогресс серым цветом - начало
                        for(int i = 0; i < 20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //закрашиваем прогресс серым цветом - конец.

                        //Определяем правильные ответы и закрашиваем зеленным цветом - начало
                        for(int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленным цветом - конец


                    }else{
                        //если нижняя правая картинка меньше
                        if(count > 0){
                            if(count == 1){
                                count = 0;
                            }else{
                                count = count - 2;
                            }
                        }
                        //закрашиваем прогресс серым цветом - начало
                        for(int i = 0; i < 19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //закрашиваем прогресс серым цветом - конец.

                        //Определяем правильные ответы и закрашиваем зеленным цветом - начало
                        for(int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленным цветом - конец
                    }

                    //Если отпустил палец - конец
                    if(count == 20){
                        //ВЫХОД ИЗ УРОВНЯ
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);
                        if(level > 2){
                            //пустр
                        }else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 3);
                            editor.commit();
                        }
                        dialogEnd.show();
                    }else{
                        //Цикл с предусловием, проверяющий равенство событий - начало
                        numLeft = random.nextInt(19);//генерируем случайное число от 0 до 16.
                        img_left.setImageResource(array.images2[numLeft]);//достаем из массива картинку
                        text_left.setText(array.texts2[numLeft]);//достаем из массива текст

                        numRight = random.nextInt(19);//генерируем случайное число от 0 до 16.
                        while(numLeft == numRight){
                            numRight = random.nextInt(19);
                            if(numLeft == numRight){
                                continue;
                            }
                        }
                        img_right.setImageResource(array.images2[numRight]);//достаем из массива картинку
                        text_right.setText(array.texts2[numRight]);//достаем из массива текст

                        numDownLeft = random.nextInt(19);//генерируем случайное число от 0 до 16.
                        while(numLeft == numDownLeft || numRight == numDownLeft){
                            numDownLeft = random.nextInt(19);
                            if(numLeft == numDownLeft || numRight == numDownLeft){
                                continue;
                            }
                        }
                        img_down_left.setImageResource(array.images2[numDownLeft]);//достаем из массива картинку
                        text_down_left.setText(array.texts2[numDownLeft]);//достаем из массива текст

                        numDownRight = random.nextInt(19);//генерируем случайное число от 0 до 16.
                        while(numLeft == numDownRight || numRight == numDownRight || numDownLeft == numDownRight){
                            numDownRight = random.nextInt(19);
                            if(numLeft == numDownRight || numRight == numDownRight || numDownLeft == numDownRight){
                                continue;
                            }
                        }
                        img_down_right.setImageResource(array.images2[numDownRight]);//достаем из массива картинку
                        text_down_right.setText(array.texts2[numDownRight]);//достаем из массива текст
                        //Цикл с предусловием, проверяющий равенство чисел - конец

                        img_left.setEnabled(true);//включаем обратно левую картинку
                        img_right.setEnabled(true);//включаем обратно правую картинку
                        img_down_left.setEnabled(true);//включаем обратно нижнюю правую картинку
                    }
                }
                //условие касания кратинки - конец
                return true;
            }
        });
        //обрабатываем нажатие на нижнюю правую картинку - конец

    }
    //системная кнопка "назад" начало
    @Override
    public void onBackPressed(){
        //обрабатываем нажатие кнопки назад - начало
        try{
            //вернуться к началу выбора уровня - начало.
            Intent intent = new Intent(Level2.this, GameLevels.class);//создали намерение для перехода
            startActivity(intent);//старт намерения
            finish();
            //вернуться к началу выбора уровня - конец.
        }catch (Exception e){
            //пусто
        }
        //обрабатываем нажатие кнопки назад - конец
    }
    //системная кнопка "назад" конец

}

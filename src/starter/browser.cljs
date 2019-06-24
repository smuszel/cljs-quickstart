(ns starter.browser)

(defn setVal [v el]
    (set! (.-value el) v)
    (identity el)    
)

(defn handleClick [handler el]
    (.addEventListener el "click" handler)
    (identity el)    
)

(defn setClass [cls el]
    (set! (.-className el) cls)
    (identity el)
)

(defn createInput []
    (.createElement js/document "input")
)

(defn append [elA elB]
    (.appendChild elB elA)
    (identity elB)
)

(defn enterHandle [inp cont]
    (.addEventListener inp "keydown" (fn [ev] 
        (if (= (.-key ev) "Enter") 
            (.appendChild cont 
                (let [$ (.createElement js/document "span")]
                    (set! (.-textContent $) (.-value inp))
                    (set! (.-value inp) "")
                    (set! (.-className $) "token")
                    (.addEventListener $ "click" #(.remove $))
                    (.appendChild cont $)
                )
            )    
            nil
        )
    ))
    (identity cont)
)

(defn addContainer [inp]
    (->> (.createElement js/document "div")
        (setClass "token-container")
        (handleClick #(.focus inp))
        (append inp)
        (enterHandle inp)
        (.appendChild js/document.body)
    )
)

;; start is called by init and after code reloading finishes
(defn ^:dev/after-load start [])

(defn ^:export init []
    (addContainer (createInput))
    (js/console.log "init")
    (start)
)

(defn ^:dev/before-load stop []
    (js/console.log "stop")
)

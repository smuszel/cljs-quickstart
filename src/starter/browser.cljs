(ns starter.browser)

(defn setVal [v el]
    (set! (.-value el) v)
    (identity el)    
)

(defn addEl [tag]
    (->> (.createElement js/document tag)
        (setVal "")
        (.appendChild js/document.body)
    )
)

;; start is called by init and after code reloading finishes
(defn ^:dev/after-load start []
  (addEl "input"))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (js/console.log "init")
  (start))

;; this is called before any code is reloaded
(defn ^:dev/before-load stop []
  (js/console.log "stop"))

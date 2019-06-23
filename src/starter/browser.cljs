(ns starter.browser)

(defn addEl [tag v]
    (let [el (.createElement js/document tag)]
        (set! (.-value el) v)
        (.appendChild js/document.body el)
    )
)

;; start is called by init and after code reloading finishes
(defn ^:dev/after-load start []
  (addEl "input", "abc"))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (js/console.log "init")
  (start))

;; this is called before any code is reloaded
(defn ^:dev/before-load stop []
  (js/console.log "stop"))

ex01 Logging: String concat without Parameters -> observe efforts for String concatenation
    start with --optimized=true for optimized code
ex02 Lock Contention: synchronized method -> observe lock contention
    new Executor services with new threadpools are created
    start with --optimized=true for optimized code
ex03 Deadlock
    start with --optimized=true for optimized code
ex04 Memory Leak, observe steadily increasing memory consumption
ex05 Web Service localhost:8080/customers mit einer langsamen Methode -> start apache jmeter mit ex05.jmx
ex06 JPA query mit lazy loading und einer One-To-One Beziehung
    start with --optimized=true for optimized code
       derby -> Performance Steigerung um Faktor 10 mit Fetch Joins
       h2 -> Performance Steigerung um Faktor 2 mit Fetch Joins
ex07 Caching mit 1 Sekunde Cache Dauer
       h2 -> Response dauert nur noch 1 ms (Faktor 50)
ex08 Caching mit Spring @Cachebale
ex09 JMX Beans Spring
ex09a JMX Java Standard
ex10 Spring Actuator
ex11 Performance Messung mit Aspekten
    http://localhost:8080/actuator/health
ex12 Performance Steigerung mit asynchroner Kommunikation
ex13 Parallelisierung InitDB mit Java Parallel Streams, Correletion Ids mit Spring Sleuth
    start with --optimized=true for optimized code (parallel stream)
    Schnellere Inserts durch Parallelisierung nur bei derbydb sichtbar, nicht bei h2
ex14 Vergleich synchronized versus ReentrantReadWriteLock (Experimentiere mit Parametern, wann welches Konstrukt besser ist)
Webflux Spring WebFlux
PerformanceDiesUndDas

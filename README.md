# MeditActive
 
gestire obiettivi personali e prenotazioni. Gli utenti possono visualizzare, decidere, eliminare obiettivi e generare report CSV con gli obiettivi disponibili. Il progetto include un sistema di assegnazione di ID univoci alle prenotazioni e un meccanismo per aggiornare la disponibilità degli obiettivi.

Struttura del Progetto

    models: Contiene le classi di modello User, Goal, e Booking.
    controllers: Contiene le classi Users, Goals, Bookings e actions che gestiscono la logica dell'applicazione.
    csv: Directory contenente i file CSV per utenti, obiettivi e prenotazioni.

Requisiti

    Java Development Kit (JDK): Versione 8 o superiore
    PowerShell: Per eseguire i comandi suggeriti (o un terminale compatibile)

Utilizzo //
Menu Principale

L'applicazione presenta un menu principale dove l'utente può selezionare diverse operazioni:

    Stampa di tutti gli obiettivi
    Decidi un obiettivo esistente
    Rimuovi un obiettivo
    Aggiungi un nuovo utente
    Esporta un file con gli obiettivi disponibili
    Esci

Funzionalità Principali

    Decidere un obiettivo: Assegna un obiettivo a un utente e crea una prenotazione.
    Rimuovere un obiettivo: Cancella un obiettivo e aggiorna la sua disponibilità.
    Esportazione CSV: Esporta un file CSV con tutti gli obiettivi disponibili.


export const endpoints = {
    LOGIN : "/authenticate",
    REGISTER : "/register",
    CREATE_GIG : "/gigs/create-gig",
    CREATE_MUSICIAN: "/musicians/create",
    CREATE_ORGANIZER: "/organizers/create/",
    CREATE_BAND: "/band-administration/create",
    GET_BAND: "/bands/",
    EDIT_BAND: "/band-administration/edit",
    GET_MUSICIAN_BASIC: "musicians/show/basic/",
    GET_BAND_ID: "/bands/like/",
    GET_MY_GIGS: "/gigs/",
    INVITE_TO_GIG: "/gigs/invite",
    GET_BAND_GIGS: "/bands/invitations/",
    GET_GIG: "/gigs/view/",
    ACCEPT_GIG: "/bands/invitations/accept",
    CANCEL_GIG: "/bands/invitations/cancel",
    GET_BANDS_LEAD: "/bands/my/leader",
    GET_MUSICIAN: "/musicians/find",
    INVITE_MAIN_MEMB: "/band-administration/invite",
    INVITE_BACKUP_MEMB: "/band-administration/invite-as-backup",
    GET_BAND_INVITATIONS: "/musicians/invitations",
    BANDS_FILTER: "/bands/filter",
    PUBLIC_GIGS_VIEW : "/gigs/get-public",
    ACCEPT_BAND_INVITE: "/band-administration/join/",
    EDIT_GIG: "/gigs/edit/",
    DECLINE_BAND_INVITE: "/musicians/invitations/cancel/"
    /*TO DO: ostali endpointi*/
}

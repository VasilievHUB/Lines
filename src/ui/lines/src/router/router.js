import MainPage from '@/pages/MainPage';
import LinesPage from '@/pages/LinesPage';
import {createRouter, createWebHistory} from "vue-router"

const routes= [
    {
    path: '/',
    component: MainPage
},
{
    path: '/LinesPage',
    component: LinesPage
},
]

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

export default router;
import MainPage from '@/pages/MainPage';
import LinesPage from '@/pages/LinesPage';
import SubsPage from '@/pages/SubsPage';
import OscillogramView from '@/pages/OscillogramView';
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
{
    path: '/SubsPage',
    component: SubsPage
},
{
    path: '/OscillogramView',
    component: OscillogramView
},
]

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

export default router;
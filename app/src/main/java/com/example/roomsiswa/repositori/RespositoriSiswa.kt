package com.example.roomsiswa.repositori

import com.example.roomsiswa.room.Siswa
import com.example.roomsiswa.room.SiswaDao
import kotlinx.coroutines.flow.Flow

interface RespositoriSiswa {
    fun getAllSiswaStream(): Flow<List<Siswa>>
    suspend fun insertSiswa(siswa: Siswa)
}

class OfflineRepositorySiswa(private val siswaDao: SiswaDao) : RespositoriSiswa {
    override fun getAllSiswaStream(): Flow<List<Siswa>> = siswaDao.getAllSiswa()
    override suspend fun insertSiswa(siswa: Siswa) = siswaDao.insert(siswa)

    //edit 3: tambah 3 override berikut
    override fun getSiswaStream(id: Int): Flow<Siswa?> = siswaDao.getSiswa(id)
    override suspend fun updateSiswa(siswa: Siswa) = siswaDao.update(siswa)
    override suspend fun deleteSiswa(siswa: Siswa) = siswaDao.delete(siswa
}
